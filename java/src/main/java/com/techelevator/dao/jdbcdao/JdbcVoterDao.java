package com.techelevator.dao.jdbcdao;

import javax.validation.OverridesAttribute;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.VoterDao;

@Component
public class JdbcVoterDao implements VoterDao {
    private JdbcTemplate template;

    public JdbcVoterDao(JdbcTemplate template) {
        this.template = template;
    }
}
