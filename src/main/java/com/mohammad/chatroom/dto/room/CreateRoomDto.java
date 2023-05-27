package com.mohammad.chatroom.dto.room;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomDto {

    private String name;

    @NotEmpty
    private Set<Long> users;
}
