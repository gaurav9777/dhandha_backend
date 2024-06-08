package com.pg.backend.service;

import com.pg.backend.dto.CommonResponse;

public interface UserService {

    CommonResponse getUserDetail(Long userId);
}
