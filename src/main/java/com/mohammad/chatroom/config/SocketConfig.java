//package com.mohammad.chatroom.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.corundumstudio.socketio.SocketIOServer;
//
//@Configuration
//public class SocketConfig {
//    @Value("${socket.host}")
//    private String socketHost;
//
//    @Value("${socket.port}")
//    private int socketPort;
//
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname(socketHost);
//        config.setPort(socketPort);
//        return new SocketIOServer(config);
//    }
//}
