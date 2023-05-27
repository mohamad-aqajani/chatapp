package com.mohammad.chatroom.dto.room;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRoomsDto {

    private Long userId;

    @Nullable
    private int page;

    @Nullable
    private int limit;
}
