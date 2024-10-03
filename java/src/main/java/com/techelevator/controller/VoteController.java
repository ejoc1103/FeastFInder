package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.VoteDao;

@RestController
@CrossOrigin
public class VoteController {

    @Autowired
    private VoteDao voteDao;
}