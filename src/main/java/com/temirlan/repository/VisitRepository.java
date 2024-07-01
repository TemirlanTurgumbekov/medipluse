package com.temirlan.repository;

import com.temirlan.mappers.MedicalCardRowMapper;
import com.temirlan.mappers.MedicalDetailMapper;
import com.temirlan.mappers.VisitDetailRowMapper;
import com.temirlan.mappers.VisitRowMapper;
import com.temirlan.models.MedicalCard;
import com.temirlan.models.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addVisit(Visit visit) {
        String sql = "INSERT INTO Visits (medicalcardid, symptoms, visitdate, diagnosis, treatment, status, doctorID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, visit.getMedicalCardID(), visit.getSymptoms(), visit.getVisitDate(), visit.getDiagnosis(), visit.getTreatment(), visit.getStatus(), visit.getDoctorID());
    }

    public List<Visit> findAllPatientVisits(int id) {
        String sql = "SELECT v.id AS id, CONCAT(d.firstname, ' ', d.lastname) AS doctorname, v.visitdate as visitdate,v.status\n" +
                "FROM Visits v\n" +
                "JOIN Doctors d ON v.doctorID = d.doctorid\n" +
                "WHERE v.medicalcardid = ?;";

        return jdbcTemplate.query(sql, new Object[]{id}, new VisitRowMapper());
    }

    public Visit findById(int id) {
        String sql = "SELECT * FROM Visits WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new VisitDetailRowMapper());
    }
}
