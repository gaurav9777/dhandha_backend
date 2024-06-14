package com.pg.backend.service;

import com.pg.backend.dto.Response.CommonResponse;
import com.pg.backend.dto.Request.AddUserRequestDto;

public interface UserService {

    CommonResponse getUserDetail(Long userId);

    CommonResponse addNewUser(AddUserRequestDto addUserRequestDto);

    CommonResponse getAllUsers(Integer pageNo,Integer pageSize);
}
