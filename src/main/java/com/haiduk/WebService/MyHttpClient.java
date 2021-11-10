package com.haiduk.WebService;

import com.haiduk.ConvertorService.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MyHttpClient {
    final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void doGet(int id){
        final HttpUriRequest httpGet = new HttpGet(String.format("http://jsonplaceholder.typicode.com/posts/%s",id));
        try {
            CloseableHttpResponse response1 = httpClient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();

//            JsonParser jsonParser1 = new JsonParser();
//
//            Article article1 = jsonParser1.fromJson(EntityUtils.toString(entity1));
//            System.out.println(article1);
            System.out.println(EntityUtils.toString(entity1));
            httpClient.close();

        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }
    public void doPost(int id)  {

        final HttpPost httpPost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
        final List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", String.format("%s",id)));
        params.add(new BasicNameValuePair("title", "some title"));
        params.add(new BasicNameValuePair("body", "some message"));
        params.add(new BasicNameValuePair("userId", "1"));
        ;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        try {
            try (
                    CloseableHttpResponse response2 = httpClient.execute(httpPost)
            ){
                final HttpEntity entity2 = response2.getEntity();
                JsonParser jsonParser = new JsonParser();

                Article article = jsonParser.fromJson(EntityUtils.toString(entity2));
                System.out.println(article);

//                System.out.println(EntityUtils.toString(entity2));
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }





}
