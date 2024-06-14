package com.pg.backend.service.impl;

import com.pg.backend.dto.Response.AllUserResponse;
import com.pg.backend.dto.Response.CommonResponse;
import com.pg.backend.dto.Request.AddUserRequestDto;
import com.pg.backend.entity.Rooms;
import com.pg.backend.entity.Users;
import com.pg.backend.exception.ClientException;
import com.pg.backend.repository.RoomRepository;
import com.pg.backend.repository.UserRepository;
import com.pg.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Users> users = userRepository.findById(userId);
        if (!users.isPresent()) {
            throw new ClientException("user is not present for given userId");
        }
        return CommonResponse.builder()
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .response(users)
                .build();
    }

    @Override
    public CommonResponse addNewUser(AddUserRequestDto addUserRequestDto) {
        Optional<Rooms> optionalRooms = roomRepository.findById(addUserRequestDto.getRoomId());
        if (!optionalRooms.isPresent()) {
            throw new ClientException("Room is not there with this id!!");
        }
        Rooms rooms = optionalRooms.get();
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
        rooms.setPeopleCount(rooms.getPeopleCount()+1);
        roomRepository.save(rooms);
        return CommonResponse.builder()
                .message(SUCCESS_MESSAGE)
                .statusCode(SUCCESS_STATUS_CODE)
                .build();
    }

    @Override
    public CommonResponse getAllUsers(Integer pageNo,Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Users> users = userRepository.findAll(pageable);
        AllUserResponse allUserResponse = new AllUserResponse();
        allUserResponse.setUsers(users.getContent());
        allUserResponse.setCount(users.getSize());
        allUserResponse.setPage(pageNo);
        allUserResponse.setNextPage(pageable.next().getPageNumber());
        allUserResponse.setPrevPage(pageable.previousOrFirst().getPageNumber());
        return CommonResponse
                .builder()
                .response(allUserResponse)
                .statusCode(SUCCESS_STATUS_CODE)
                .message(SUCCESS_MESSAGE)
                .build();
    }
}