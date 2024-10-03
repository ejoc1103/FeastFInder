package com.techelevator.dao.dao;

import java.util.List;

import com.techelevator.model.Groups;

public interface GroupsDao {
    List<Groups> getGroups(int id);
}
