package com.pg.backend.CsvUsingThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping(path = "/upsert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOrUpdateData(@RequestPart(value = "file") MultipartFile file) throws Exception {
        long startTime = System.currentTimeMillis();
        csvService.upsertData(file);
        long endTime = System.currentTimeMillis();
        return new ResponseEntity<>((endTime-startTime), HttpStatus.OK);
    }
}
