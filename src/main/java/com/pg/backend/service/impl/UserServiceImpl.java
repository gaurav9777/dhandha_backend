package com.pg.backend.service.impl;

import com.pg.backend.dto.CommonResponse;
import com.pg.backend.entity.Users;
import com.pg.backend.repository.UserRepository;
import com.pg.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pg.backend.constants.ApplicationConstants.SUCCESS_MESSAGE;
import static com.pg.backend.constants.ApplicationConstants.SUCCESS_STATUS_CODE;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommonResponse getUserDetail(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return CommonResponse.builder()
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .response(users)
                .build();
    }
}
