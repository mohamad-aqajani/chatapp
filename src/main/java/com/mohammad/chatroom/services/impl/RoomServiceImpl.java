package com.mohammad.chatroom.services.impl;

import com.mohammad.chatroom.dto.room.CreateRoomDto;
import com.mohammad.chatroom.dto.room.CreateRoomResponse;
import com.mohammad.chatroom.dto.room.GetRoomResponse;
import com.mohammad.chatroom.entities.Room;
import com.mohammad.chatroom.entities.User;
import com.mohammad.chatroom.exceptions.NotFoundException;
import com.mohammad.chatroom.repositories.RoomRepository;
import com.mohammad.chatroom.repositories.UserRepository;
import com.mohammad.chatroom.services.RoomService;
import com.mohammad.chatroom.utils.RetreiveUseInterceptor;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private ModelMapper modelMapper;

    @Override
    public CreateRoomResponse createRoom(CreateRoomDto createRoomDto) {
        createRoomDto.getUsers().forEach((userId) -> {
            if (userRepository.findById(userId).isEmpty())
                throw new NotFoundException("User with id " + userId + " not found");
        });
        Room room = new Room();
        room.setName(createRoomDto.getName());

        List<User> users = userRepository.findAllById(createRoomDto.getUsers());
        users.add(RetreiveUseInterceptor.getUser());

        room.getUsers().addAll(users);
        Room newRoom = roomRepository.save(room);
        CreateRoomResponse response = new CreateRoomResponse();
        /* assign object */
        response.setId(newRoom.getId());
        response.setName(newRoom.getName());
        response.setUsers(newRoom.getUsers().stream().map(User::getId).collect(Collectors.toSet()));

        users.forEach((user) -> {
            user.getRooms().add(newRoom);
            userRepository.save(user);
        });

        return response;
    }

    @Override
    public GetRoomResponse getRoom(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room with id " + id + " not found"));
        GetRoomResponse response = new GetRoomResponse();
        response.setId(room.getId());
        response.setName(room.getName());
        response.setUsers(room.getUsers().stream().map(userRooms -> {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("id", userRooms.getId());
            userInfo.put("username", userRooms.getUsername());
            return userInfo;
        }).collect(Collectors.toSet()));
        return response;
    }

    @Override
    public Set<GetRoomResponse> getUserRooms() {
        User user = RetreiveUseInterceptor.getUser();
        List<Room> rooms = roomRepository.findByUsersId(user.getId());
        Set<GetRoomResponse> response = new HashSet<>();
        rooms.stream().map((room) -> {
            return response.add(
                    new GetRoomResponse(room.getId(), room.getName(), room.getUsers().stream().map(userRooms -> {
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("id", userRooms.getId());
                        userInfo.put("username", userRooms.getUsername());
                        return userInfo;
                    }).collect(Collectors.toSet()))
            );
        }).collect(Collectors.toSet());
        return response;
    }
}
