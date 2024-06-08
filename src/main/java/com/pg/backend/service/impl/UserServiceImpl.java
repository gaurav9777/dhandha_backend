package com.pg.backend.service.impl;

import com.pg.backend.dto.CommonResponse;
import com.pg.backend.dto.Request.AddUserRequestDto;
import com.pg.backend.entity.Rooms;
import com.pg.backend.entity.Users;
import com.pg.backend.repository.RoomRepository;
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

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public CommonResponse getUserDetail(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return CommonResponse.builder()
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .response(users)
                .build();
    }

    @Override
    public CommonResponse addNewUser(AddUserRequestDto addUserRequestDto) {
        Rooms rooms = roomRepository.findById(addUserRequestDto.getRoomId()).orElseThrow(()->new RuntimeException("Room is not there with this id!!"));
            Users users = Users.builder()
                    .firstName(addUserRequestDto.getFirstName())
                    .lastName(addUserRequestDto.getLastName())
                    .age(addUserRequestDto.getAge())
                    .mobileNumber(addUserRequestDto.getMobileNumber())
                    .email(addUserRequestDto.getEmail())
                    .gender(addUserRequestDto.getGender())
                    .city(addUserRequestDto.getCity())
                    .state(addUserRequestDto.getState())
                    .profilePicture(addUserRequestDto.getProfilePicture())
                    .dateOfJoining(addUserRequestDto.getDateOfJoining())
                    .lastDate(addUserRequestDto.getLastDate())
                    .roomRentStatus(addUserRequestDto.getRoomRentStatus())
                    .room(rooms).build();
        userRepository.save(users);
        return CommonResponse.builder()
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .build();
    }
}