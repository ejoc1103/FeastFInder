package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Eatery;
import org.springframework.stereotype.Component;

@Component
public class JdbcEateryDao implements EateryDao {
    @Override
    public List<Eatery> list() {
        return null;
    }
}
