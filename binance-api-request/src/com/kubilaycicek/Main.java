package com.kubilaycicek;

import com.kubilaycicek.request.BinanceWebSocketClient;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //BinanceHttpClient.sendRequest();
        //BinanceHttpClient.sendAsyncRequest();
        BinanceWebSocketClient.sendRequest();
    }
}
