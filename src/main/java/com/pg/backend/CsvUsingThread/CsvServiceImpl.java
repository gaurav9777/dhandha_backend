package com.pg.backend.CsvUsingThread;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class CsvServiceImpl implements CsvService {

    @Autowired
    private CsvRepository csvRepository;

    @Autowired
    @Qualifier("csvExecutor")
    private ThreadPoolTaskExecutor csvExecutor;

    @Override
    public void upsertData(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("file is empty");
        }
        if (!Objects.requireNonNull(file.getOriginalFilename().endsWith(".csv"))) {
            throw new Exception("file must be csv");
        }
        List<String[]> data = prepareData(file);
        processData(data);
        return;
    }

    private void processData(List<String[]> data) throws Exception {
        List<CsvRequestDto> dtoFromCsv = createDtoFromCsv(data);
        List<CsvDataEntity> dataToBeSaved = new ArrayList<>();
        for (CsvRequestDto dto : dtoFromCsv) {
            Integer id = dto.getId();
            Optional<CsvDataEntity> optionalCsvDataEntity = csvRepository.findById(id);
            if (optionalCsvDataEntity.isEmpty()) {
                //create
                CsvDataEntity csvDataEntity = CsvDataEntity.builder()
                        .name(dto.getName())
                        .age(dto.getAge())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                dataToBeSaved.add(csvDataEntity);
            } else {
                //update
                CsvDataEntity csvDataEntity = optionalCsvDataEntity.get();
                csvDataEntity.setName(dto.getName());
                csvDataEntity.setAge(dto.getAge());
                csvDataEntity.setUpdatedAt(LocalDateTime.now());
                dataToBeSaved.add(csvDataEntity);
            }
        }
        csvRepository.saveAll(dataToBeSaved);
    }

//    private List<CsvRequestDto> createDtoFromCsv(List<String[]> data) {
//        List<CsvRequestDto> requestDtos = new ArrayList<>();
//        for (String[] item : data) {
//            requestDtos.add(CsvRequestDto.builder().id(Integer.valueOf(item[0])).name(item[1]).build());
//        }
//        return requestDtos;
//    }


    private List<CsvRequestDto> createDtoFromCsv(List<String[]> data) throws Exception {
        int chunkSize = 200;
        List<Future<List<CsvRequestDto>>> futures = new ArrayList<>();

        for (int i = 0; i < data.size(); i += chunkSize) {
            List<String[]> subList = data.subList(i, Math.min(i + chunkSize, data.size()));

            futures.add(csvExecutor.submit(() -> {
                List<CsvRequestDto> dtos = new ArrayList<>();
                for (String[] item : subList) {
                    dtos.add(CsvRequestDto.builder()
                            .id(Integer.valueOf(item[0]))
                            .name(item[1])
                            .build());
                }
                return dtos;
            }));
        }

        List<CsvRequestDto> allDtos = new ArrayList<>();
        for (Future<List<CsvRequestDto>> f : futures) {
            allDtos.addAll(f.get());
        }
        return allDtos;
    }


    private List<String[]> prepareData(MultipartFile file) throws IOException, CsvException {
        Map<String, Object> preparedData = new HashMap<>();
        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
        List<String[]> rows = csvReader.readAll();
        List<String[]> data = new ArrayList<>();

        if (!CollectionUtils.isEmpty(rows)) {
            String[] csvHeader = rows.get(0);
            if (!csvHeader[0].equalsIgnoreCase("id") || !csvHeader[1].equalsIgnoreCase("name")) {
                throw new RuntimeException("header is not correct");
            }

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                data.add(row);
            }
        }
        return data;
    }
}
