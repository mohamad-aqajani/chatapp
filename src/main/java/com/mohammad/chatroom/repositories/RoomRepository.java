package com.mohammad.chatroom.repositories;

import com.mohammad.chatroom.dto.room.GetRoomResponse;
import com.mohammad.chatroom.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface RoomRepository extends JpaRepository<Room, Long> {

    //    @Query("SELECT r.id, r.users, u.id, u.email FROM Room r JOIN r.users u WHERE u.id = :userId")
    public List<Room> findByUsersId(Long userId);

}
