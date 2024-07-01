package com.temirlan.repository;


import com.temirlan.mappers.AppointmentRowMapper;
import com.temirlan.mappers.PatientRowMapper;
import com.temirlan.models.Appointments;
import com.temirlan.models.Doctor;
import com.temirlan.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Patients> findAll() {
        return jdbcTemplate.query("SELECT * FROM patients", new PatientRowMapper());
    }

    public Patients findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM patients WHERE patientid = ?",
                new Object[]{id},
                new PatientRowMapper()
        );
    }

    public void addPatient(Patients patients) {
        String sql = "INSERT INTO Patients (firstname, lastname, phone, email) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, patients.getName(), patients.getLastname(), patients.getPhone(), patients.getEmail());
    }
}
