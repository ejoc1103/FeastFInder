package com.techelevator.controller;

import java.util.List;

import com.techelevator.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.EateryDao;
import com.techelevator.model.Eatery;
import com.techelevator.security.jwt.TokenProvider;
import com.techelevator.services.YelpService;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class EateryController {

    @Autowired
    private EateryDao eateryDao;
    @Autowired
    private YelpService yelpService;


    @RequestMapping(path = "/restaurants/{data}", method= RequestMethod.GET)
    public List<Eatery> getEateryList(@Valid @PathVariable String data) {

        return yelpService.getEateries(data);

    }
}