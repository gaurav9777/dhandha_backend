package com.pg.backend.service;

import com.pg.backend.dto.CommonResponse;
import com.pg.backend.dto.Request.AddUserRequestDto;

public interface UserService {

    CommonResponse getUserDetail(Long userId);

    CommonResponse addNewUser(AddUserRequestDto addUserRequestDto);
}
