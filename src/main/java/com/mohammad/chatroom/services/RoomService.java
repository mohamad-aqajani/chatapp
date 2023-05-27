package com.mohammad.chatroom.services;

import com.mohammad.chatroom.dto.room.CreateRoomDto;
import com.mohammad.chatroom.dto.room.CreateRoomResponse;
import com.mohammad.chatroom.dto.room.GetRoomResponse;
import com.mohammad.chatroom.entities.Room;

import java.util.Set;

public interface RoomService {
    public CreateRoomResponse createRoom(CreateRoomDto createRoomDto);

    public GetRoomResponse getRoom(Long id);

    public Set<GetRoomResponse> getUserRooms();
}
