package com.pg.backend.service.impl;

import com.pg.backend.dto.Response.AllRoomsResponse;
import com.pg.backend.dto.Response.CommonResponse;
import com.pg.backend.entity.Rooms;
import com.pg.backend.entity.Users;
import com.pg.backend.repository.RoomRepository;
import com.pg.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public CommonResponse getAllRooms(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Rooms> allRooms = roomRepository.findAll(pageable);

        AllRoomsResponse allRoomsResponse = new AllRoomsResponse();
        allRoomsResponse.setRooms(allRooms.getContent());
        allRoomsResponse.setPageNo(pageNo);
        allRoomsResponse.setPageSize(pageSize);
        allRoomsResponse.setNextPage(pageable.next().getPageNumber());
        allRoomsResponse.setPrevPage(pageable.previousOrFirst().getPageNumber());

        return CommonResponse.builder()
                .response(allRoomsResponse)
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .build();
    }
}
