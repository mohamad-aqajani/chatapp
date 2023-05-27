package com.mohammad.chatroom.dto.room;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateRoomResponse {
    private Long id;
    private String name;
    private Set<Long> users;
}
