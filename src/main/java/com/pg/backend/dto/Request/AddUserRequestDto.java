package com.pg.backend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequestDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private String mobileNumber;

    private String email;

    private String gender;

    private String city;

    private String state;

    private String profilePicture;

    private Long DateOfJoining;

    private Long lastDate;

    private String roomRentStatus;

    private Long roomId;
}
