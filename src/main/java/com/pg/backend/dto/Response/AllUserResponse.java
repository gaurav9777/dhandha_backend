package com.pg.backend.dto.Response;

import com.pg.backend.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllUserResponse {

    private List<Users> users;
    private int page;
    private int nextPage;
    private int prevPage;
    private int count;
}
