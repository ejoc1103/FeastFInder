package com.techelevator.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Eatery;


@Component
public class YelpService {
    @Value("${yelp.api.key}")
    private String apiKey;
    private final int limit = 50;
    @Value("${yelp.api.url}")
    private String apiUrl;

     public List<Eatery> getEateries(String searchString){

        String url = this.apiUrl + "?location=" + searchString + "&limit=" + this.limit;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.apiKey);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        // These two are new! We'll see how they work.
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        List <Eatery> eateryList = new ArrayList<>();

        String jsonResponseBody = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
        try {
            jsonNode = objectMapper.readTree(jsonResponseBody);
            JsonNode root = jsonNode.path("businesses");

            for(int i = 0; i < root.size(); i++){
                eateryList.add(mapEatery(root,i));
            }
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return eateryList;

    };
    private Eatery mapEatery(JsonNode root, int i) {
        String id = root.path(i).path("id").asText();
                String name = root.path(i).path("name").asText();
                String imageUrl = root.path(i).path("image_url").asText();
                //Make a loop after display_address to build the string
                String address =  buildAsset(root.path(i).path("location"));
                JsonNode categories = root.path(i).path("categories");
                String category = "";
                for(JsonNode cat : categories) {
                    category += cat.path("title").asText() + " ";
                }
                String openTime = "";
                String closeTime = "";
                for(JsonNode time : root.path(i).path("business_hours").path(0).path("open")) {
                    openTime += time.path("start").asText() + " ";
                    closeTime += time.path("end").asText() + " ";
                }
                String hasTakeout = buildAsset(root.path(i).path("transactions"));
                String price = root.path(i).path("price").asText();
                String rating = root.path(i).path("rating").asText();
                String phoneNumber = root.path(i).path("display_phone").asText();
                String isClosed = root.path(i).path("is_closed").asText();
                return new Eatery(id, name, imageUrl, address, category, openTime, closeTime, hasTakeout, rating, phoneNumber, price, isClosed);
    }
    private String buildAsset(JsonNode assetArray) {
        String asset = "";
        for(JsonNode add : assetArray) {
            asset += add.asText() + " ";
        }
        return asset.trim();
    }
}
