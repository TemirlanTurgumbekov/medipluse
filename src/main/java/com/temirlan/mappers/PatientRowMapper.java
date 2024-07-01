package com.temirlan.mappers;


import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.temirlan.models.Patients;

public class PatientRowMapper implements RowMapper<Patients> {
    @Override
    public Patients mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patients patient = new Patients();
        patient.setId(rs.getInt("patientid"));
        patient.setName(rs.getString("firstname"));
        patient.setLastname(rs.getString("lastname"));
        patient.setPhone(rs.getString("phonenumber"));
        return patient;
    }
}
