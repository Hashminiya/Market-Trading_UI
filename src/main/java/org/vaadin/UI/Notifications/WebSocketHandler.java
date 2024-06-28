package org.vaadin.UI.Notifications;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.vaadin.UI.Util.Credentials;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WebSocketHandler {
    private static ClientEndPoint clientEndPoint;
    private static WebSocketClient webSocketClient;
    private static List<MessageListener> messageListeners = new ArrayList<>();
    public static void openConnection() {
        HttpClient httpClient = new HttpClient();
        webSocketClient = new WebSocketClient(httpClient);

        try {
            webSocketClient.start();
            clientEndPoint = new ClientEndPoint();
            clientEndPoint.addMessageListeners(messageListeners);
            String username = Credentials.getUserName();
            URI serverURI = URI.create("ws://localhost:8080/ws?username=" + username);
            CompletableFuture<Session> clientSessionPromise = webSocketClient.connect(clientEndPoint, serverURI);
            clientSessionPromise.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            if (clientEndPoint != null) {
                Session session = clientEndPoint.getSession();
                if (session != null && session.isOpen()) {
                    session.close();
                }
            }
            if (webSocketClient != null && webSocketClient.isStarted()) {
                webSocketClient.stop();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error closing WebSocket connection", e);
        } finally {
            clientEndPoint = null;
            webSocketClient = null;
        }
    }
    public static void addListener(MessageListener listener){
        messageListeners.add(listener);
    }
}
