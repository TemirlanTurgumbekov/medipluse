package com.temirlan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class PatientService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int createPatient(String surname, String name, String email, String phone) {
        final String sql = "INSERT INTO patients (firstname, lastname, email, phonenumber) VALUES (?, ?, ?, ?) RETURNING patientid";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"patientid"}); // Указание, что возвращать нужно только patientid
            ps.setString(1, surname);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // Возвращает сгенерированный patientid
    }

}
