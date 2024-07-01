package com.temirlan.repository;


import com.temirlan.mappers.DoctorRowMapper;
import com.temirlan.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Метод для получения доктора по email
    public Doctor findByEmail(String email) {
        String sql = "SELECT * FROM Doctors WHERE email = ?";
        List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorRowMapper(), email);
        return doctors.isEmpty() ? null : doctors.get(0);
    }

    // Добавление нового доктора
    public void addDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors (firstname, lastname, specialty, address, degree, education, publications, characteristics, email, password, role, avatar, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctor.getFirstName(), doctor.getLastName(), doctor.getSpecialty(), doctor.getAddress(), doctor.getDegree(), doctor.getEducation(), doctor.getPublications(), doctor.getCharacteristics(), doctor.getEmail(), doctor.getPassword(), doctor.getStatus(), doctor.getAvatar(), doctor.getExperience());
    }

    // Обновление данных доктора
    public void updateDoctor(Doctor doctor) {
        String sql = "UPDATE Doctors SET firstname=?, lastname=?, specialty=?, address=?, degree=?, education=?, publications=?, characteristics=?, email=?, experience=? WHERE doctorid=?";

        jdbcTemplate.update(sql, doctor.getFirstName(), doctor.getLastName(), doctor.getSpecialty(), doctor.getAddress(), doctor.getDegree(), doctor.getEducation(), doctor.getPublications(), doctor.getCharacteristics(), doctor.getEmail(), doctor.getExperience(), doctor.getDoctorId());
    }

    // Удаление доктора по ID
    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM Doctors WHERE doctorid = ?";
        jdbcTemplate.update(sql, doctorId);
    }

    // Получение всех докторов
    public List<Doctor> findAll() {
        String sql = "SELECT * FROM Doctors";
        return jdbcTemplate.query(sql, new DoctorRowMapper());
    }

    // Получение доктора по ID
    public Doctor findById(int doctorId) {
        String sql = "SELECT * FROM Doctors WHERE doctorid = ?";
        List<Doctor> doctors = jdbcTemplate.query(sql, new DoctorRowMapper(), doctorId);
        return doctors.isEmpty() ? null : doctors.get(0);
    }
}
