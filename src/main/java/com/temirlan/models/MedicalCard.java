package com.temirlan.models;

import java.time.LocalDate;
import java.util.Date;

public class MedicalCard {
    private int id;
    private String fullname;
    private String phonenumber;
    private String pin;
    private Date dob;
    private String address;
    private String gender;
    private String workplace;
    private String profession;
    private int doctorId;
    private String status;
    private String doctor;

    public MedicalCard() {
    }

    public MedicalCard(int id, String fullname, String phonenumber, String pin, Date dob, String address, String gender, String workplace, String profession, int doctorId, String status, String doctor) {
        this.id = id;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.pin = pin;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.workplace = workplace;
        this.profession = profession;
        this.doctorId = doctorId;
        this.status = status;
        this.doctor = doctor;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MedicalCard{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", pin=" + pin +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", workplace='" + workplace + '\'' +
                ", profession='" + profession + '\'' +
                ", doctorId=" + doctorId +
                ", status='" + status + '\'' +
                '}';
    }
}
