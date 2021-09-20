package com.kubilaycicek.request;

import com.kubilaycicek.constants.StringConstant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BinanceHttpClient {

    private BinanceHttpClient(){}
    public static final AtomicInteger counter = new AtomicInteger();

    public static void sendRequest() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(StringConstant.URL))
                .header(StringConstant.ACCEPT, StringConstant.APPLICATION_JSON)
                .build();
        var start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            System.err.println(response);
        }
        var stop = System.currentTimeMillis();
        System.err.println("Duration : " + (stop - start) + ".msec");
    }

    public static void sendAsyncRequest() throws  InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(StringConstant.URL))
                .header(StringConstant.ACCEPT, StringConstant.APPLICATION_JSON)
                .build();

        var start = System.currentTimeMillis();
        for (int i = 0; i < 10; ++i) {
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAcceptAsync(response -> {
                        System.err.println(response.body());
                        if (counter.incrementAndGet() >= 10) {
                            var stop = System.currentTimeMillis();
                            System.err.println("Duration: " + (stop - start) / 10. + "msec.");
                        }
                    }, Executors.newFixedThreadPool(32));
        }
        System.err.println("All request are sent!");
        TimeUnit.SECONDS.sleep(10);
        System.err.println("Done.");
    }
}
