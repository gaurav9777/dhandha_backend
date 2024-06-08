package com.pg.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pg.backend.utils.CustomDateTimeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String mobileNumber;

    private String email;

    private String gender;

    private String city;

    private String state;

    private String profilePicture;

    @Convert(converter = CustomDateTimeConverter.class)
    @Column(name = "date_of_joining")
    private Long DateOfJoining;

    @Convert(converter = CustomDateTimeConverter.class)
    @Column(name = "last_date")
    private Long lastDate;

    private String roomRentStatus;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Rooms room;

}
