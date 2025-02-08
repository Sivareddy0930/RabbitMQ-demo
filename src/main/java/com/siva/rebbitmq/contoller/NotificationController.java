package com.siva.rebbitmq.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {


    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Method to send message to the topic "/topic/notifications"


    public void sendMessage(String message) {
        logger.info("Message received from the topic: " + message);
        messagingTemplate.convertAndSend("/topic/notifications", message);  // Broadcasting to clients
    }
}
