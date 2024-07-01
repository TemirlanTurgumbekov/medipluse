package com.temirlan.models;

import java.sql.Date;
import java.time.LocalDate;

public class BlogPost {
    private Integer id;
    private Integer doctorId;
    private String title;
    private String content;
    private String photoUrl;
    private Date postDate;

    public BlogPost() {
    }

    public BlogPost(Integer id, Integer doctorId, String title, String content, String photoUrl, Date postDate) {
        this.id = id;
        this.doctorId = doctorId;
        this.title = title;
        this.content = content;
        this.photoUrl = photoUrl;
        this.postDate = postDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", doctorId=" + doctorId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", postDate=" + postDate +
                '}';
    }
}

