package com.techelevator.dao.jdbcdao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.dao.GroupsDao;
import com.techelevator.model.Groups;

@Component
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
            if (results.next()) {
                return mapRowToTransfer(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return null;
    }
}
