package com.temirlan.controllers;

import com.temirlan.dao.AppointmentDAO;
import com.temirlan.dao.DoctorDAO;
import com.temirlan.dao.ScheduleDAO;
import com.temirlan.models.BlogPost;
import com.temirlan.models.Doctor;
import com.temirlan.models.Visit;
import com.temirlan.models.MedicalCard;
import com.temirlan.models.Patients;
import com.temirlan.repository.*;
import com.temirlan.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerControllers {
    private final AppointmentDAO appointmentDAO;
    private final DoctorDAO doctorDAO;
    private final DoctorService doctorService;
    private final BlogPostRepository blogPostRepository;
    private final PatientRepository patientRepository;
    private final MedicalRepository medicalRepository;
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;


    @Autowired
    public WorkerControllers(AppointmentDAO appointmentDAO, DoctorDAO doctorDAO, DoctorService doctorService, BlogPostRepository blogPostRepository, PatientRepository patientRepository, MedicalRepository medicalRepository, VisitRepository visitRepository, DoctorRepository doctorRepository) {
        this.appointmentDAO = appointmentDAO;
        this.doctorDAO = doctorDAO;
        this.doctorService = doctorService;
        this.blogPostRepository = blogPostRepository;
        this.patientRepository = patientRepository;
        this.medicalRepository = medicalRepository;
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        Doctor doctor = doctorService.getCurrentDoctor(); // Метод для получения текущего врача
        if (doctor == null) {
            // Обработка случая, когда врач не найден
            return "redirect:/worker/login"; // или другая страница с сообщением об ошибке
        }
        model.addAttribute("status", doctor.getStatus());
        System.out.println("Status" + doctor.getStatus());
        model.addAttribute("appointments", appointmentDAO.index(doctor.getDoctorId()));
        return "worker/schedule/index";
    }

    @GetMapping("login")
    public String login(@ModelAttribute("doctor") Doctor doctor) {
        return "worker/login";
    }

    @GetMapping("patient/new")
    public String newPatient(@ModelAttribute("medical") MedicalCard medicalCard) {
        return "worker/patient/medical_card";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("doctors", doctorDAO.index());
        return "worker/profile";
    }

    @GetMapping("/doctor/new")
    public String newDoctor(@ModelAttribute("doctor") Doctor doctor) {
        return "worker/doctors/new";
    }

    @GetMapping("/doctor/list")
    public String listDoctor(Model model) {
        model.addAttribute("patients", doctorDAO.index());
        return "worker/doctors/list";
    }

    @GetMapping("/doctor/{id}")
    public String detailDoctor(@PathVariable("id") int id, Model model) {

        model.addAttribute("doctor", doctorRepository.findById(id));
        return "worker/doctors/detail";
    }

    @PostMapping("/auth")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        if (doctorDAO.authenticate(email, password)) {
            session.setAttribute("email", email);
            return "redirect:/worker";
        } else {
            model.addAttribute("loginError", "Неверный логин или пароль");
            return "/worker/login";
        }
    }

    @GetMapping("/edit")
    public String editProfile(Model model) {
        Doctor doctor = doctorService.getCurrentDoctor(); // Метод для получения текущего врача
        if (doctor == null) {
            // Обработка случая, когда врач не найден
            return "redirect:/worker/login"; // или другая страница с сообщением об ошибке
        }
        model.addAttribute("doctor", doctor);
        return "/worker/profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("doctor") Doctor doctor) {
        if (doctor.getStatus() == null) {
            doctor.setStatus("doctor");
        }
        doctorService.updateDoctor(doctor);

        return "redirect:/worker/";
    }

    @PostMapping("/doctor/new")
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorRepository.addDoctor(doctor);
        return "redirect:/worker";
    }

    @GetMapping("/doctor/delete/{id}")
    public String deleteDoctor(@PathVariable("id") int id) {
        doctorRepository.deleteDoctor(id);
        return "redirect:/worker";
    }

    @GetMapping("/blog/new")
    public String createArticle(@ModelAttribute("blogPost") BlogPost blogPost) {
        return "/worker/blog/newpost";
    }

    @GetMapping("/blog/{id}")
    public String createArticle(@PathVariable("id") int id, Model model) {
        BlogPost blogPost = blogPostRepository.findById(id);
        model.addAttribute("blogpost", blogPost);
        return "/worker/blog/edit";
    }

    @GetMapping("/blog/list")
    public String listPosts(Model model) {
        Doctor doctor = doctorService.getCurrentDoctor(); // Метод для получения текущего врача
        if (doctor == null) {
            // Обработка случая, когда врач не найден
            return "redirect:/worker/login"; // или другая страница с сообщением об ошибке
        }

        List<BlogPost> blogPosts = blogPostRepository.listPosts(doctor.getDoctorId());
        model.addAttribute("blogposts", blogPosts);

        return "/worker/blog/list";
    }

    @PostMapping("/blog/add")
    public String addBlogPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        Doctor doctor = doctorService.getCurrentDoctor();
        if (doctor == null) {
            return "redirect:/worker/login";
        }

        blogPost.setDoctorId(doctor.getDoctorId());
        blogPost.setPostDate(Date.valueOf(LocalDate.now()));
        blogPostRepository.addBlogPost(blogPost);
        return "redirect:/worker";
    }

    @PostMapping("/blog/update")
    public String updateBlogPost(@ModelAttribute("blogpost") BlogPost blogPost) {
        Doctor doctor = doctorService.getCurrentDoctor();
        if (doctor == null) {
            return "redirect:/worker/login";
        }
        System.out.println("Date: " + blogPost.getId());
        blogPost.setDoctorId(doctor.getDoctorId());
        blogPost.setPostDate(Date.valueOf(LocalDate.now()));
        blogPostRepository.updateBlogPost(blogPost);
        return "redirect:/worker/blog/list";
    }

    @GetMapping("/patient/list")
    public String showPatient(Model model) {
        List<MedicalCard> patients = medicalRepository.findAllPatientDoctorDetails();
        model.addAttribute("patients", patients);
        return "worker/patient/list";
    }

    @PostMapping("/patient/new")
    public String newMedicalCard(@ModelAttribute("medical") MedicalCard medicalCard, HttpServletRequest request) {
        Doctor doctor = doctorService.getCurrentDoctor();
        if (doctor == null) {
            return "redirect:/worker/login";
        }

        medicalCard.setDoctorId(doctor.getDoctorId());
        int id = medicalRepository.addMedicalCard(medicalCard);
        request.getSession().setAttribute("idCard", id);
        return "redirect:/worker/patient/card";
    }

    @GetMapping("/patient/card")
    public String card(@ModelAttribute("visit") Visit visit) {

        return "worker/patient/card";
    }

    @PostMapping("/patient/visit/new")
    public String newVisit(@ModelAttribute("visit") Visit visit, HttpServletRequest request) {
        Doctor doctor = doctorService.getCurrentDoctor();
        if (doctor == null) {
            return "redirect:/worker/login";
        }
        Integer idCard = (Integer) request.getSession().getAttribute("idCard");
        if (idCard != null) {
            visit.setMedicalCardID(idCard);
        }

        visit.setDoctorID(doctor.getDoctorId());
        visit.setVisitDate(Date.valueOf(LocalDate.now()));
        visitRepository.addVisit(visit);
        return "redirect:/worker/patient/list";
    }

    @GetMapping("/patient/{id}")
    public String detailPatient(@PathVariable("id") int id, Model model) {
        MedicalCard medicalCard = medicalRepository.findById(id);
        if (medicalCard == null) {
            return "redirect:/worker/patient/list";
        }

        List<Visit> patients = visitRepository.findAllPatientVisits(id);

        model.addAttribute("medicalCard", medicalCard);
        model.addAttribute("patients", patients);
        return "worker/patient/show";
    }

    @GetMapping("/patient/visit/{id}")
    public String detailVisit(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Visit visit = visitRepository.findById(id);
        model.addAttribute("visit", visit);
        request.getSession().setAttribute("idCard", id);
        return "worker/patient/detailVisit";
    }
}
