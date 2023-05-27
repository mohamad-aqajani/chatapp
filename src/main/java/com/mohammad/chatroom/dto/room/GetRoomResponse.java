package com.mohammad.chatroom.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomResponse {
    private Long id;
    private String name;
    private Set<Object> users;
}
