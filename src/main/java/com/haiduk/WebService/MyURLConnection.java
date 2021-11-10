package com.haiduk.WebService;

import com.haiduk.ConvertorService.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyURLConnection {
    public void doGet(int id){
        String strURL = String.format("http://jsonplaceholder.typicode.com/posts/%s", id);
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL(strURL).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");

                }
                JsonParser jsonParser = new JsonParser();

                Article article = jsonParser.fromJson(sb.toString());
                System.out.println(article);
                System.out.println(sb.toString());

            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }


        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
