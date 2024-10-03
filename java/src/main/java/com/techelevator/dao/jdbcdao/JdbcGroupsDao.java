package com.techelevator.dao.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.GroupsDao;
import com.techelevator.model.Groups;

@Component
@PreAuthorize("isAuthenticated()")
public class JdbcGroupsDao implements GroupsDao {
    private JdbcTemplate template;

    public JdbcGroupsDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public Groups getGroup(int id) {
        String sql = "SELECT * FROM groups WHERE group_id = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            if(results.next()) {
                return mapRowToGroups(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return null;
    }
    @Override
    public List<Groups> getGroups (String name) {
        String sql = "SELECT * FROM groups JOIN user_group ON groups.group_id = user_group.group_id " +
                "JOIN users ON user_group.user_id = users.user_id " +
                "WHERE users.username = ?;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, name);
            List<Groups> groups = new ArrayList<>();
            while(results.next()) {
                groups.add( mapRowToGroups(results));

            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return null;
    }

    private Groups mapRowToGroups(SqlRowSet results) {
        Groups group = new Groups();
        group.setGroup_id(results.getInt("group_id"));
        group.setGroup_name(results.getString("group_name"));
        group.setVote_id(results.getInt("vote_id"));
        return group;
    }
}
