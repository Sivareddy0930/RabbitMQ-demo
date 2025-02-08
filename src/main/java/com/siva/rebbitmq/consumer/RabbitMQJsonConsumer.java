package com.siva.rebbitmq.consumer;

import com.siva.rebbitmq.contoller.NotificationController;
import com.siva.rebbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private final NotificationController notificationController;
//    private final WebSocketService webSocketService;

    public RabbitMQJsonConsumer(NotificationController notificationController) {
        this.notificationController = notificationController;
//        this.webSocketService = webSocketService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJson(User user) {
        LOGGER.info(String.format("Received JSON message: %s", user.toString()));

        // Check if the WebSocket is connected before proceeding
//        if (webSocketService.isConnected()) {
        // WebSocket is up, forward the message
        notificationController.sendMessage(user.toString());
//        } else {
//            LOGGER.warn("WebSocket service is down. Message will be queued for later.");
//
//            // WebSocket is not connected, enqueue the message for later processing
//            webSocketService.enqueueMessage(user.toString());
//        }
    }
}
