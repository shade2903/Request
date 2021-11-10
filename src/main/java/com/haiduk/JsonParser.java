package com.haiduk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    public static Article fromJson(String articleJson){
        Article article = new Article();
        if(!articleJson.isEmpty()){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                article = objectMapper.readValue(articleJson, Article.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        }

        return article;

    }
}
