package com.temirlan.repository;


import com.temirlan.mappers.AppointmentRowMapper;
import com.temirlan.mappers.MedicalCardRowMapper;
import com.temirlan.mappers.MedicalDetailMapper;
import com.temirlan.models.BlogPost;
import com.temirlan.models.MedicalCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class MedicalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<MedicalCard> findAllPatientDoctorDetails() {
        String sql = "SELECT \n" +
                "\tm.id,\n" +
                "    m.fullname AS fullname,\n" +
                "    d.firstname || ' ' || d.lastname AS doctor,\n" +
                "    m.status AS status\n" +
                "FROM \n" +
                "    MedicalCards m\n" +
                "JOIN \n" +
                "    Doctors d ON m.doctorId = d.doctorid;";

        return jdbcTemplate.query(sql, new MedicalCardRowMapper());
    }


    public int addMedicalCard(MedicalCard medicalCard) {
        String sql = "INSERT INTO Medicalcards (fullname, phonenumber, pin, birthdate, address, gender, workplace, profession, doctorId, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" }); // Указываем столбец, содержащий ключ
                    ps.setString(1, medicalCard.getFullname());
                    ps.setString(2, medicalCard.getPhonenumber());
                    ps.setString(3, medicalCard.getPin());
                    ps.setDate(4, (Date) medicalCard.getDob());
                    ps.setString(5, medicalCard.getAddress());
                    ps.setString(6, medicalCard.getGender());
                    ps.setString(7, medicalCard.getWorkplace());
                    ps.setString(8, medicalCard.getProfession());
                    ps.setInt(9, medicalCard.getDoctorId());
                    ps.setString(10, "Активна");
                    return ps;
                },
                keyHolder
        );

        return keyHolder.getKey().intValue();
    }

    public MedicalCard findById(int id) {
        String sql = "SELECT * FROM MedicalCards WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MedicalDetailMapper());
    }
}
