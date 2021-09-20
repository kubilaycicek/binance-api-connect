package com.kubilaycicek.request;

import com.kubilaycicek.constants.StringConstant;
import com.kubilaycicek.listener.BinanceWebSocketListener;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.concurrent.TimeUnit;

public class BinanceWebSocketClient {
    public static void sendRequest() throws InterruptedException {
        HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(URI.create(StringConstant.URL_STREAM_BINANCE),new BinanceWebSocketListener());

        TimeUnit.MINUTES.sleep(1);

    }

}
