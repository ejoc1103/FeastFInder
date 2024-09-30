package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.EateryDao;
import com.techelevator.model.Eatery;
import com.techelevator.security.jwt.TokenProvider;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EateryController {
    
    @Autowired
    private EateryDao eateryDao;
    private final TokenProvider tokenProvider;

    public EateryController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @RequestMapping(path = "/restaurants")
    public List<Eatery> getEateryList() {
        return eateryDao.list();
    }
}