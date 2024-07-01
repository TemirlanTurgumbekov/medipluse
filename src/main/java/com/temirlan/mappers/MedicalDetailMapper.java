package com.temirlan.mappers;

import com.temirlan.models.MedicalCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalDetailMapper implements RowMapper<MedicalCard> {
    @Override
    public MedicalCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(rs.getInt("id"));
        medicalCard.setFullname(rs.getString("fullname"));
        medicalCard.setPhonenumber(rs.getString("phonenumber"));
        medicalCard.setPin(rs.getString("pin"));
        medicalCard.setDob(rs.getDate("birthdate"));
        medicalCard.setAddress(rs.getString("address"));
        medicalCard.setGender(rs.getString("gender"));
        medicalCard.setWorkplace(rs.getString("workplace"));
        medicalCard.setProfession(rs.getString("profession"));
        medicalCard.setStatus(rs.getString("status"));

        return medicalCard;
    }
}
