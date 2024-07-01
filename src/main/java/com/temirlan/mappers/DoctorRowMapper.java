package com.temirlan.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.temirlan.models.Doctor;
import org.springframework.jdbc.core.RowMapper;

public class DoctorRowMapper implements RowMapper<Doctor> {
    @Override
    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(rs.getInt("doctorid"));
        doctor.setFirstName(rs.getString("firstname"));
        doctor.setLastName(rs.getString("lastname"));
        doctor.setSpecialty(rs.getString("specialty"));
        doctor.setAddress(rs.getString("address"));
        doctor.setDegree(rs.getString("degree"));
        doctor.setEducation(rs.getString("education"));
        doctor.setPublications(rs.getString("publications"));
        doctor.setCharacteristics(rs.getString("characteristics"));
        doctor.setEmail(rs.getString("email"));
        doctor.setPassword(rs.getString("password"));
        doctor.setAvatar(rs.getString("avatar"));
        doctor.setStatus(rs.getString("role"));
        doctor.setExperience(rs.getString("experience"));
        return doctor;
    }
}
