package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.GroupsDao;
import com.techelevator.model.Eatery;
import com.techelevator.model.Groups;
import com.techelevator.services.YelpService;

@RestController
@CrossOrigin
public class YelpController {

    @Autowired
    private GroupsDao eateryDao;
    @Autowired
    private YelpService yelpService;
    
    @RequestMapping(path = "/restaurants/{data}", method= RequestMethod.GET)
    public List<Eatery> getEateryList(@Valid @PathVariable String data) {
        return yelpService.getEateries(data);
    }

    @RequestMapping(path = "/groups/{id}", method= RequestMethod.GET)
    public List<Groups> getGroups(@Valid @PathVariable int id) {
        return eateryDao.getGroups(id);
    }
}