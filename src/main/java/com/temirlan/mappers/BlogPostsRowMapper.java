package com.temirlan.mappers;

import com.temirlan.models.BlogPost;
import com.temirlan.models.Patients;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogPostsRowMapper implements RowMapper<BlogPost> {
    @Override
    public BlogPost mapRow(ResultSet rs, int rowNum) throws SQLException {
        BlogPost patient = new BlogPost();
        patient.setId(rs.getInt("id"));
        patient.setDoctorId(rs.getInt("doctorid"));
        patient.setTitle(rs.getString("title"));
        patient.setContent(rs.getString("content"));
        patient.setPhotoUrl(rs.getString("photo_url"));
        patient.setPostDate(rs.getDate("post_date"));
        return patient;
    }
}
