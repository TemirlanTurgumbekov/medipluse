package com.temirlan.dao;


import com.temirlan.models.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Schedules> index() {
        return jdbcTemplate.query("SELECT * FROM schedules", new BeanPropertyRowMapper<>(Schedules.class));
    }

    public Schedules show(int id) {
        return jdbcTemplate.query("SELECT * FROM scedule WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Schedules.class))
                .stream().findAny().orElse(null);
    }

//    public void save(Schedule person) {
//        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)", person.getName(), person.getAge(),
//                person.getEmail());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
}
