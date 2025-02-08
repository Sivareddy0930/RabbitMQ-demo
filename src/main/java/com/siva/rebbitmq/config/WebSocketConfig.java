package com.siva.rebbitmq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // This will broadcast messages to "/topic"
        config.setApplicationDestinationPrefixes("/app"); // Prefix for routing messages from clients

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // The WebSocket endpoint to consume message from topic
//                .setAllowedOrigins("*") // Allow all origins for WebSocket connection
                .setAllowedOrigins("http://localhost:3000")// specific origin to consume message from ws endpoint.
                .withSockJS(); // Enable SockJS for fallback
    }
}
