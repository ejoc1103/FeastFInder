package com.techelevator.dao.dao;

import com.techelevator.model.Voter;


public interface VoterDao {
    Voter addVoter(Voter voter);
    Voter getVoter(int id);
}
