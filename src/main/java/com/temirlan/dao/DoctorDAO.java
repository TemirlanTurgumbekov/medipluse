package com.temirlan.dao;

import com.temirlan.models.Doctor;
import com.temirlan.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class DoctorDAO {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HttpSession session;

    public Doctor getCurrentDoctor() {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            return doctorRepository.findByEmail(email);
        }
        return null; // или выбросить исключение, если врач не найден
    }
    public List<Doctor> index() {
        return doctorRepository.findAll();
    }

    public boolean authenticate(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);
        return doctor != null && doctor.getPassword().equals(password);
    }
}
