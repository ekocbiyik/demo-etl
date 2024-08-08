package com.ttgint.core;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EtlCoreService {

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    public int getRandomInt() {
        return new Random().nextInt(100 - 70 + 1) + 70;
    }

    public Response sendRequest(String httpUrl, String method, String message) throws IOException {

        Request.Builder request = "post".equalsIgnoreCase(method)
                ? new Request.Builder().url(httpUrl).post(RequestBody.create(message, MediaType.get("application/json; charset=utf-8")))
                : new Request.Builder().url(httpUrl).get();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .callTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();

        return client.newCall(request.build()).execute();
    }

}
