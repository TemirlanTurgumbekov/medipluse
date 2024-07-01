package com.temirlan.dao;


import com.temirlan.models.Appointments;
import com.temirlan.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class AppointmentDAO {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointments> index(int doctorId) {
        return appointmentRepository.getAppointmentsWithPatientNames(doctorId);
    }
}
