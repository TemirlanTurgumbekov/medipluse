package com.temirlan.service;

import com.temirlan.models.Doctor;
import com.temirlan.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HttpSession session;

    public Doctor getCurrentDoctor() {
        String email = (String) session.getAttribute("email");
        System.out.println("Attempting to retrieve doctor for email: " + email);
        if (email != null) {
            Doctor doctor = doctorRepository.findByEmail(email);
            if (doctor != null) {
                System.out.println("Doctor retrieved: " + doctor.getFirstName() + " " + doctor.getLastName());
            } else {
                System.out.println("No doctor found for email: " + email);
            }
            return doctor;
        } else {
            System.out.println("No email found in session.");
            return null; // или выбросить исключение, если врач не найден
        }
    }

    public void updateDoctor(Doctor doctor) {
        doctorRepository.updateDoctor(doctor);
    }
}
