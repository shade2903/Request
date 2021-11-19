package com.haiduk.WebService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haiduk.ConvertorService.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MyURLConnection {
    HttpURLConnection connection = null;
    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferReader;

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
    public void doPost(int id){
        String strURL = String.format("http://jsonplaceholder.typicode.com/posts/");

        StringBuilder stringBuilder = new StringBuilder();



        Article article = new Article();
        article.setUserId(2);
        article.setId(id);
        article.setTitle("some title");
        article.setBody("some message");



        String articleToString = null;
        try {
            articleToString = new ObjectMapper().writeValueAsString(article);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            connection = (HttpURLConnection) new URL(strURL).openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = articleToString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferReader = new BufferedReader(inputStreamReader);

            String inputLine;

            while ((inputLine = bufferReader.readLine()) != null) {
                stringBuilder.append(inputLine);

            }
            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
