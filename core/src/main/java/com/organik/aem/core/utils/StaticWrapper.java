package com.organik.aem.core.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class StaticWrapper {

    public static final StaticWrapper SINGLETON = new StaticWrapper();

    public HttpGet getHttpGet(String path) {
        return new HttpGet(path);
    }

    public HttpPost getHttpPost(String path) {
        return new HttpPost(path);
    }

    public CloseableHttpClient createDefaultHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(30000)
            .setSocketTimeout(30000)
            .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).useSystemProperties().build();
    }

    public JsonObject getJsonFromString(String jsonString) throws JsonSyntaxException {
        return new Gson().fromJson(jsonString, JsonObject.class);
    }
}