package com.temirlan.controllers;


import com.temirlan.dao.DoctorDAO;
import com.temirlan.models.Appointments;
import com.temirlan.models.BlogPost;
import com.temirlan.models.Doctor;
import com.temirlan.models.Patients;
import com.temirlan.repository.AppointmentRepository;
import com.temirlan.repository.BlogPostRepository;
import com.temirlan.repository.DoctorRepository;
import com.temirlan.repository.PatientRepository;
import com.temirlan.service.EmailService;
import com.temirlan.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class pageControllers {
    private final DoctorDAO doctorDAO;
    private final DoctorRepository doctorRepository;
    private final PatientService patientService;
    private final AppointmentRepository appointmentRepository;
    private final BlogPostRepository blogPostRepository;

    public pageControllers(DoctorDAO doctorDAO, PatientService patientService, AppointmentRepository appointmentRepository, BlogPostRepository blogPostRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorDAO = doctorDAO;
        this.patientService = patientService;
        this.appointmentRepository = appointmentRepository;
        this.blogPostRepository = blogPostRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping()
    public String index(Model model) {
        List<BlogPost> blogPost = blogPostRepository.lists3();
        model.addAttribute("blogPosts", blogPost);
        return "main/index";
    }

    @GetMapping("/doctors")
    public String listOfDoctors(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "main/doctors/list";
    }

    @GetMapping("/doctors/{id}")
    public String detailDoctor(@PathVariable int id, Model model) {
        Doctor doctor = doctorRepository.findById(id);
        model.addAttribute("doctor", doctor);
        return "main/doctors/detail";
    }

    @GetMapping("/contact")
    public String about() {
        return "main/contact";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        List<BlogPost> blogPost = blogPostRepository.lists();
        model.addAttribute("articles", blogPost);
        return "main/listarticle";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable("id") int id, Model model) {
        BlogPost blogPost = blogPostRepository.findById(id);
        Doctor doctor = doctorRepository.findById(blogPost.getDoctorId());
        model.addAttribute("doctor", doctor);
        model.addAttribute("blogPost", blogPost);
        return "main/article";
    }

    @GetMapping("/success")
    public String success(Model model) {
        return "main/successPage";
    }

    @GetMapping("/record")
    public String Record(Model model) {
        model.addAttribute("doctors", doctorDAO.index());

        return "main/record";
    }

    @GetMapping("/subscribe")
    public String subscribe() {
        return "main/subscribe";
    }

    @GetMapping("/portfolio")
    public String portfolio(){
        return "main/portfolio-details";
    }

//    @PostMapping("/send-email")
//    public String sendEmail(@RequestParam String email, @RequestParam String name) {
//        String subject = "Подтверждение отправки";
//        String message = "Успешно отправлено";
//
//        emailService.sendEmail(email, subject, message);
//
//        return "redirect:/";
//    }

    @PostMapping("/record/add")
    public String addRecord(
            @RequestParam("surname") String lastname,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("doctorId") int doctorId,
            @RequestParam("date") LocalDate date,
            @RequestParam("time") LocalTime time
    ) {
        int patientId = patientService.createPatient(name, lastname, email, phone);
        appointmentRepository.addAppointment(patientId, doctorId, date, time, "Scheduled");

        return "redirect:/success";
    }
}
