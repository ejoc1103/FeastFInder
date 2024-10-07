package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.VoterDao;
import com.techelevator.model.Voter;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class VoterController {
    
    @Autowired
    private VoterDao voterDao;

    @RequestMapping(path = "/voter", method = RequestMethod.POST)
    public Voter addVoter(@RequestBody Voter voter) {
        return voterDao.addVoter(voter);
    }

    @RequestMapping(path = "/voter", method = RequestMethod.GET)
    public Voter getVoter(int id) {
        return voterDao.getVoter(id);
    }
}
