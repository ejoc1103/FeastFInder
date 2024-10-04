package com.techelevator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.dao.EateryDao;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class EateryController {
   // @Autowired
    private EateryDao eateryDao;
}