package com.temirlan.repository;


import com.temirlan.mappers.AppointmentRowMapper;
import com.temirlan.models.Appointments;
import com.temirlan.models.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class AppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Appointments> getAppointmentsWithPatientNames(int doctorId) {
        String sql = "SELECT a.appointmentid, a.patientid, a.doctorid, a.appointmentdate, a.appointmenttime, a.status, " +
                "p.firstname, p.lastname " +
                "FROM Appointments a " +
                "JOIN Patients p ON a.patientid = p.patientid WHERE a.doctorid = ?";

        return jdbcTemplate.query(sql, new Object[]{doctorId}, new AppointmentRowMapper());
    }

    public void addAppointment(int patientId, int doctorId, LocalDate date, LocalTime time, String status) {
        String sql = "INSERT INTO Appointments (patientid, doctorid, appointmentdate, appointmenttime, status) VALUES (?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, patientId, doctorId, date, time, status);
    }
}
