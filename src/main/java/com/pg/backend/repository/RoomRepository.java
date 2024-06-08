package com.pg.backend.repository;

import com.pg.backend.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms,Long> {
    Rooms findByRoomId(Long roomId);
}
