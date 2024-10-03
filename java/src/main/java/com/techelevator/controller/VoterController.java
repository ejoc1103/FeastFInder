package com.techelevator.controller;

import com.techelevator.dao.dao.VoterDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class VoterController {
    //@AutoWired
    private VoterDao voterDao;
}