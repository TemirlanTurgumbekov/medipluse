package com.temirlan.models;

import java.sql.Date;
import java.time.LocalDate;



public class Visit {
    private int id;
    private int medicalCardID;
    private String symptoms;
    private Date visitDate;
    private String diagnosis;
    private String treatment;
    private String status;
    private int doctorID;
    private String doctorName;

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }


    public Visit() {
    }

    public Visit(int id, int medicalCardID, String symptoms, Date visitDate, String diagnosis, String treatment, String status, int doctorID, String doctorName) {
        this.id = id;
        this.medicalCardID = medicalCardID;
        this.symptoms = symptoms;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.status = status;
        this.doctorID = doctorID;
        this.doctorName = doctorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicalCardID() {
        return medicalCardID;
    }

    public void setMedicalCardID(int medicalCardID) {
        this.medicalCardID = medicalCardID;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
