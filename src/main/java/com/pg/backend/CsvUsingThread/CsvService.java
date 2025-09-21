package com.pg.backend.CsvUsingThread;

import org.springframework.web.multipart.MultipartFile;

public interface CsvService {
    void upsertData(MultipartFile file) throws Exception;
}
