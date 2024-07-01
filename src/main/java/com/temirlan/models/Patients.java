package com.temirlan.models;

import java.time.LocalDate;

public class Patients {
    private int id;
    private String name;
    private String lastname;
    private LocalDate dateofbirth;
    private String phone;
    private String email;

    public Patients() {
    }

    public Patients(int id, String name, String lastname, LocalDate dateofbirth, String phone, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
