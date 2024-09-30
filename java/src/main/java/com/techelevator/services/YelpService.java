package com.techelevator.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.model.Eatery;


public class YelpService {
    
    private final String apiKey = "s-6tgUeg0KaH0SRa0ugTKBd3hWT9r51vvHf-ljwSnjtvcfEN9NcBy75Sn5WTLUstvU81iNM40hTaArVqNLzgYV_aPbDxkSopW5ImVZdPrf1Z81elmqeXJED_S9P6ZnYx";
    
    private final String YELP_API_URL = "https://api.yelp.com/v3/businesses/search";

    private final HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Eatery> getEateries(String data) {
        headers.setBearerAuth(this.apiKey);
        HttpEntity<String> entity = new HttpEntity<>("parameters", this.headers);
        String url = this.YELP_API_URL + "?location=" + data + "&categories=restaurants&limit=25";
        ResponseEntity<Eatery> response = restTemplate.exchange(url, HttpMethod.GET, entity, Eatery.class);
        List<Eatery> eateries = new ArrayList<>();
        for(Eatery eatery : response.getBody()) {
            eateries.add(eatery);
        }
        return response.getBody().;
    }
}
