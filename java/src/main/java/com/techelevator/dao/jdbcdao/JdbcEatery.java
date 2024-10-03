package com.techelevator.dao.jdbcdao;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@PreAuthorize("isAuthenticated()")
public class JdbcEatery {
    
}
