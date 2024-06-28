package org.vaadin.UI.Notifications;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class ClientEndPoint implements WebSocketListener {
    private Session session;
    private final List<MessageListener> messageListeners = new ArrayList<>();

    @Override
    public void onWebSocketConnect(Session session) {
        this.session = session;
        session.setIdleTimeout(Duration.ofSeconds(0));
        System.out.println("Connected to server");
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        System.out.println("Connection closed: " + reason);
        this.session = null;
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        // Handle binary message
    }

    @Override
    public void onWebSocketText(String message) {
        System.out.println("Received message: " + message);
        notifyMessageListeners(message);
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            session.getRemote().sendString(message, null);
        }
    }
    private void notifyMessageListeners(String message) {
        for (MessageListener listener : messageListeners) {
            listener.onMessageReceived(message);
        }
    }

    public void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }

    public Session getSession() {
        return session;
    }

    public void addMessageListeners(List<MessageListener> messageListeners) {
        this.messageListeners.addAll(messageListeners);
    }
}
