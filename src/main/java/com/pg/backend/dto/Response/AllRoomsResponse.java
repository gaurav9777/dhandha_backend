package com.pg.backend.dto.Response;

import com.pg.backend.entity.Rooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllRoomsResponse {
    private List<Rooms> rooms;
    private int pageNo;
    private int pageSize;
    private int count;
    private int nextPage;
    private int prevPage;
}
