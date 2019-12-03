package com.example.demo.service;

import java.io.IOException;

import okhttp3.*;

public class OkHttpPost {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("https://fcm.googleapis.com/fcm/send")
        		.addHeader("Authorization", "key=AIzaSyB_o3SFfp55xLl8KRqAOBTTH6ejG9VTJoU")
        		.post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
