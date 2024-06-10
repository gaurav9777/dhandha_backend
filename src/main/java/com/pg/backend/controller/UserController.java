package com.pg.backend.controller;

import com.pg.backend.dto.CommonResponse;
import com.pg.backend.dto.Request.AddUserRequestDto;
import com.pg.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/detail")
    public ResponseEntity<?> userDetailsById(@RequestParam("userId") Long userId){
        CommonResponse response = userService.getUserDetail(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> addNewUser(@RequestBody AddUserRequestDto addUserRequestDto){
        CommonResponse response = userService.addNewUser(addUserRequestDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
