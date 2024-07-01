package com.temirlan.mappers;

import com.temirlan.models.Appointments;
import com.temirlan.models.MedicalCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalCardRowMapper implements RowMapper<MedicalCard> {
    @Override
    public MedicalCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getInt("id"));
        medicalCard.setFullname(rs.getString("fullname"));
        medicalCard.setDoctor(rs.getString("doctor"));
        medicalCard.setStatus(rs.getString("status"));

        return medicalCard;
    }
}
