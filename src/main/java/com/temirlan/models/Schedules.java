package com.temirlan.models;


public class Schedules {
    private int scheduleId;
    private int doctorId;
    private String availableDate;
    private String availableTime;

    public Schedules() {
    }

    public Schedules(int id, int doctor_id, String available_date, String available_time) {
        this.scheduleId = id;
        this.doctorId = doctor_id;
        this.availableDate = available_date;
        this.availableTime = available_time;
    }

    public int getId() {
        return scheduleId;
    }

    public void setId(int id) {
        this.scheduleId = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
