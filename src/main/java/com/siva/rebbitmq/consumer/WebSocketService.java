/*
package com.siva.rebbitmq.consumer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import com.siva.rebbitmq.contoller.NotificationController;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class WebSocketService {

    private boolean isConnected = false;  // Track WebSocket connection status
    private Queue<String> messageQueue = new LinkedList<>();  // Queue to hold messages temporarily
    private final NotificationController notificationController;

    // Constructor injection for NotificationController
    public WebSocketService(NotificationController notificationController) {
        this.notificationController = notificationController;
    }

    // Set WebSocket connection status and process queued messages if WebSocket is connected
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
        if (isConnected) {
            processQueuedMessages();  // Process any stored messages when WebSocket reconnects
        }
    }

    // Check if WebSocket is connected
    public boolean isConnected() {
        return isConnected;
    }

    // Enqueue a message to be sent later
    public void enqueueMessage(String message) {
        messageQueue.add(message);  // Store the message in the queue
        System.out.println("Message enqueued: " + message);
    }

    // Process all queued messages when WebSocket is available
    public void processQueuedMessages() {
        if (messageQueue.isEmpty()) {
            System.out.println("No messages to process.");
            return;
        }

        System.out.println("Processing queued messages...");

        while (!messageQueue.isEmpty()) {
            String message = messageQueue.poll();  // Get the next message from the queue
            notificationController.sendMessage(message);  // Send it to WebSocket clients
            System.out.println("Processed message: " + message);
        }
    }

    @EventListener
    public void onWebSocketConnect(SessionConnectedEvent event) {
        // WebSocket is now connected
        setConnected(true);
        System.out.println("WebSocket connected: " + event.getMessage());
    }

    @EventListener
    public void onWebSocketDisconnect(SessionDisconnectEvent event) {
        // WebSocket is disconnected
        setConnected(false);
        System.out.println("WebSocket disconnected: " + event.getMessage());
    }
}
*/
