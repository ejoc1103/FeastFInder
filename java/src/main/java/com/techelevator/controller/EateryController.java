package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.EateryDao;
import com.techelevator.model.Eatery;
import com.techelevator.security.jwt.TokenProvider;
import com.techelevator.services.YelpService;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EateryController {
    
    @Autowired
    private EateryDao eateryDao;
    private final TokenProvider tokenProvider;
    @Autowired
    private YelpService yelpService;

    public EateryController(TokenProvider tokenProvider, YelpService yelpService, EateryDao eateryDao) {
        this.tokenProvider = tokenProvider;
        this.yelpService = yelpService;
        this.eateryDao = eateryDao;
    }

    @RequestMapping(path = "/restaurants", method= RequestMethod.GET)
    public List<Eatery> getEateryList(@RequestBody String data) {
        return yelpService.getEateries(data);
    }
}