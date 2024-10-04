package com.techelevator.dao.dao;

import com.techelevator.model.Vote;

public interface VoteDao {
    Vote getVote(int id);
    Vote addVote(Vote vote);
}
