package com.kubilaycicek.listener;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;

// Reactive Listener
public class BinanceWebSocketListener implements WebSocket.Listener {
    @Override
    public void onOpen(WebSocket webSocket) {
        System.err.println("Connected");
        webSocket.request(1);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.err.println(data);
        webSocket.request(1);
        return null;
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.err.println("Connection is closed!");
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        System.err.println("Error: " + error.getMessage());
    }
}
