package com.vumanhcuong.rssfeedreader.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    private static HttpHandler sInstance = null;

    private HttpHandler() {
    }

    public static HttpHandler getInstance() {
        if (sInstance == null) {
            sInstance = new HttpHandler();
        }
        return sInstance;
    }

    public String getData(String urlString) {
        HttpURLConnection conn = null;
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (MalformedURLException e) {
            Log.e("Error", String.format("Failed to process URL: \"%s\"", urlString), e);
        } catch (IOException e) {
            Log.e("Error", String.format("Failed to connect URL: \"%s\"", urlString), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return content.toString();
    }
}
