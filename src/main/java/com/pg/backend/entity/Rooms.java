package com.pg.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "rooms")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomPaymentStatus; // paid , partial_Paid , pending , unpaid

    private Long peopleCount;

    private Integer floor;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Users> users = new ArrayList<>();
}
