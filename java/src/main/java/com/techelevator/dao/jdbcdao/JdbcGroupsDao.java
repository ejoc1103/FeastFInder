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
    public List<Groups> getGroups(int id) {
        String sql = "SELECT * FROM groups;";
        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            List<Groups> groups = new ArrayList<>();
            while(results.next()) {
                groups.add(mapRowToGroups(results));
            }
            return groups;
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
