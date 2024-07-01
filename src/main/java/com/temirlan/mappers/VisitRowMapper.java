package com.temirlan.mappers;


import com.temirlan.models.MedicalCard;
import com.temirlan.models.Visit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitRowMapper implements RowMapper<Visit> {
    @Override
    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setId(rs.getInt("id"));
        visit.setVisitDate(rs.getDate("visitdate"));
        visit.setDoctorName(rs.getString("doctorname"));
        visit.setStatus(rs.getString("status"));

        return visit;

    }
}
