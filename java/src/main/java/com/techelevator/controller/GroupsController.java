package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.GroupsDao;
import com.techelevator.model.Groups;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class GroupsController {

    @Autowired
    private GroupsDao groupsDao;
    
    //return group from 
    @RequestMapping(path = "/groups/{id}", method= RequestMethod.GET)
    public List<Groups> getGroups(@Valid @PathVariable int id) {
        return groupsDao.getGroups(id);
    }

    //call all of groups from that user
}