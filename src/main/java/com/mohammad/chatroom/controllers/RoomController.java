package com.mohammad.chatroom.controllers;

import com.mohammad.chatroom.dto.room.CreateRoomDto;
import com.mohammad.chatroom.dto.room.CreateRoomResponse;
import com.mohammad.chatroom.dto.room.GetRoomResponse;
import com.mohammad.chatroom.entities.Room;
import com.mohammad.chatroom.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/rooms")
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody(required = true) CreateRoomDto requestBody) {
        return new ResponseEntity<>(roomService.createRoom(requestBody), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRoomResponse> getRoom(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.getRoom(id), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Set<GetRoomResponse>> getUserRooms() {
        return new ResponseEntity<>(roomService.getUserRooms(), HttpStatus.OK);
    }
}
