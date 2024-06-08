package com.pg.backend.service;

import com.pg.backend.dto.CommonResponse;

public interface RoomService {

    CommonResponse getRoomDetails(Long roomId);
}
