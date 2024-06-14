package com.pg.backend.controller;

import com.pg.backend.dto.Response.CommonResponse;
import com.pg.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/detail")
    public ResponseEntity<?> roomDetailById(@RequestParam("roomId") Long roomId){
        CommonResponse response = roomService.getRoomDetails(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
