package com.pg.backend.service.impl;

import com.pg.backend.dto.Response.CommonResponse;
import com.pg.backend.entity.Rooms;
import com.pg.backend.repository.RoomRepository;
import com.pg.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pg.backend.constants.ApplicationConstants.SUCCESS_MESSAGE;
import static com.pg.backend.constants.ApplicationConstants.SUCCESS_STATUS_CODE;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public CommonResponse getRoomDetails(Long roomId) {
         Rooms roomsData = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
         return CommonResponse.builder()
                 .message(SUCCESS_MESSAGE)
                 .statusCode(SUCCESS_STATUS_CODE)
                 .response(roomsData)
                 .build();
    }
}
