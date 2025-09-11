package com.pg.backend.audioBook.controller;

import com.pg.backend.audioBook.audioService.AudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/audio-book")
@Slf4j
public class AudioBookController {

    @Autowired
    private AudioService audioService;

    @PostMapping("/speek")
    public void convertToAudio(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
        audioService.readData(multipartFile);
        log.info("whole book is completed");
    }
}
