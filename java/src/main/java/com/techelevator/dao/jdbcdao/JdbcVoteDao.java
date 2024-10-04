package com.techelevator.dao.jdbcdao;

import javax.sql.DataSource;
import javax.validation.OverridesAttribute;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.VoteDao;
import com.techelevator.model.Vote;

@Component
public class JdbcVoteDao implements VoteDao{

    private JdbcTemplate template;

    public JdbcVoteDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    
    @Override
    public Vote getVote(int id) {
        String sql = "SELECT * FROM vote WHERE vote_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToVote(results);
        } else {
            return null;
        }
    }

    @Override
    public Vote addVote(Vote vote) {
        String sql = "INSERT INTO vote(vote_name, vote_description, vote_start, vote_end, eatery_id) VALUES (?, ?, ?, ?, ?) RETURNING vote_id";
        int voteId = template.queryForObject(sql, Integer.class, vote.getVote_name(), vote.getVote_description(), vote.getVote_start_date(), vote.getVote_end_date(), vote.getEatery_id());
        vote.setVote_id(voteId);
        return vote;
    }

    public Vote mapRowToVote(SqlRowSet results) {
        Vote vote = new Vote();
        vote.setVote_id(results.getInt("vote_id"));
        vote.setVote_name(results.getString("vote_name"));
        vote.setVote_description(results.getString("vote_description"));
        vote.setVote_start_date(results.getDate("vote_start_date"));
        vote.setVote_end_date(results.getDate("vote_end_date"));
        vote.setEatery_id(results.getInt("eatery_id"));
        vote.setIs_active(results.getBoolean("is_active"));
        vote.setUser_vote_id(results.getInt("user_vote_id"));
        return vote;
    }
}
