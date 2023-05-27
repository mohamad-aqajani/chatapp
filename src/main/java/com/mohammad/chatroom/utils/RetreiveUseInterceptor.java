package com.mohammad.chatroom.utils;

import com.mohammad.chatroom.entities.User;
import com.mohammad.chatroom.security.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class RetreiveUseInterceptor {
    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((CustomUser) authentication.getPrincipal()).getUser();
    }
}
