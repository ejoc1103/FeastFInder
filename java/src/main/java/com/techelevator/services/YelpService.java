package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Eatery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class YelpService {

    private final String apiKey = "s-6tgUeg0KaH0SRa0ugTKBd3hWT9r51vvHf-ljwSnjtvcfEN9NcBy75Sn5WTLUstvU81iNM40hTaArVqNLzgYV_aPbDxkSopW5ImVZdPrf1Z81elmqeXJED_S9P6ZnYx";
    private final int limit = 25;
    private final String YELP_API_URL = "https://api.yelp.com/v3/businesses/search";



     List<Eatery> eateryList = new ArrayList<>(){

        HttpEntity<String> httpEntity = new HttpEntity<>("");
            RestTemplate restTemplate = new RestTemplate();

            // These two are new! We'll see how they work.
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode;

            try {
                String jsonResponseBody = restTemplate.exchange(YELP_API_URL, HttpMethod.GET, httpEntity, String.class).getBody();
                jsonNode = objectMapper.readTree(jsonResponseBody);
                JsonNode root = jsonNode.path("businesses");
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }

            return eateryList;
        }
    };


}
