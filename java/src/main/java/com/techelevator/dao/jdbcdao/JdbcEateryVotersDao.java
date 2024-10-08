package com.techelevator.dao.jdbcdao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.EateryVotersDao;
import com.techelevator.model.Eatery_Voter;

@Component
public class JdbcEateryVotersDao implements EateryVotersDao {
    private JdbcTemplate template;

    public JdbcEateryVotersDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public Eatery_Voter addEateryVote(Eatery_Voter eateryVoter) {
        String sql = "INSERT INTO eatery_vote (eatery_id, voter_id, eatery_response) VALUES (?, ?, ?) RETURNING eatery_id;";
        int id = template.queryForObject(sql, Integer.class, eateryVoter.getEatery_id(), eateryVoter.getVoter_id(), eateryVoter.isResponse());
        return getEateryVoteById(id);
    }

    @Override
    public int getEateryTrueVotes(int eateryId) {
        String sql = "SELECT COUNT(*) FROM eatery_voter WHERE eatery_response = true AND eatery_id = ?;";
        return template.queryForObject(sql, Integer.class, eateryId);
    }

    @Override
    public int getEateryFalseVotes(int eateryId) {
        String sql = "SELECT COUNT(*) FROM eatery_voter WHERE eatery_response = false AND eatery_id = ?;";
        return template.queryForObject(sql, Integer.class, eateryId);
    }

    private Eatery_Voter getEateryVoteById(int id) {
        String sql = "SELECT * FROM eatery_vote WHERE eatery_id = ?;";
        return template.queryForObject(sql, (rs, rowNum) -> {
            Eatery_Voter eateryVoter = new Eatery_Voter();
            eateryVoter.setEatery_id(rs.getInt("eatery_id"));
            eateryVoter.setVoter_id(rs.getInt("voter_id"));
            return eateryVoter;
        }, id);
    }
}
