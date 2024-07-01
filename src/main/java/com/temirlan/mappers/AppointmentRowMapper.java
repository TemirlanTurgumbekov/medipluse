package com.temirlan.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.temirlan.models.Appointments;
import org.springframework.jdbc.core.RowMapper;

public class AppointmentRowMapper implements RowMapper<Appointments> {
    @Override
    public Appointments mapRow(ResultSet rs, int rowNum) throws SQLException {
        Appointments appointment = new Appointments();
        appointment.setAppointmentId(rs.getInt("appointmentid"));
        appointment.setPatientId(rs.getInt("patientid"));
        appointment.setDoctorId(rs.getInt("doctorid"));
        appointment.setAppointmentDate(rs.getDate("appointmentdate"));
        appointment.setAppointmentTime(rs.getTime("appointmenttime"));
        appointment.setStatus(rs.getString("status"));
        appointment.setPatientFirstName(rs.getString("firstname"));
        appointment.setPatientLastName(rs.getString("lastname"));

        return appointment;
    }
}

