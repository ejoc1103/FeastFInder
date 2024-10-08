package com.techelevator.dao.jdbcdao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.VoterDao;
import com.techelevator.model.Voter;

@Component
public class JdbcVoterDao implements VoterDao {
    private JdbcTemplate template;

    public JdbcVoterDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Voter addVoter(String voter_name) {
        String sql = "INSERT INTO voter(voter_name) VALUES (?) RETURNING voter_id";
        int voterId = template.queryForObject(sql, Integer.class, voter_name);
        return getVoter(voterId);
    }

    @Override
    public Voter getVoter(int id) {
        String sql = "SELECT * FROM voter WHERE voter_id = ?";
        return template.queryForObject(sql, (rs, rowNum) -> {
            Voter voter = new Voter();
            voter.setVoter_id(rs.getInt("voter_id"));
            voter.setVoter_name(rs.getString("voter_name"));
            return voter;
        }, id);
    }
}
