package com.pg.backend.service;

import com.pg.backend.dto.Response.CommonResponse;

public interface RoomService {

    CommonResponse getRoomDetails(Long roomId);

    CommonResponse getAllRooms(Integer pageNo, Integer pageSize);
}
