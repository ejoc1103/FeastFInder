package com.techelevator.dao.dao;

import com.techelevator.model.Voter;


public interface VoterDao {
    Voter addVoter(String voter_name);
    Voter getVoter(int id);
}
