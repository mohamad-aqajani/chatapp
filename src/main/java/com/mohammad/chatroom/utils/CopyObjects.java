package com.mohammad.chatroom.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CopyObjects {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, D> void copy(S source, D destination) {
        modelMapper.map(source, destination);
    }

}
