package com.temirlan.mappers;

import com.temirlan.models.MedicalCard;
import com.temirlan.models.Visit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VisitDetailRowMapper implements RowMapper<Visit> {
    @Override
    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Visit visit = new Visit();
        visit.setId(rs.getInt("id"));
        visit.setSymptoms(rs.getString("symptoms"));
        visit.setDiagnosis(rs.getString("diagnosis"));
        visit.setTreatment(rs.getString("treatment"));

        return visit;
    }
}
