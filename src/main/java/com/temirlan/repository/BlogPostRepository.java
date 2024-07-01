package com.temirlan.repository;

import com.temirlan.mappers.BlogPostsRowMapper;
import com.temirlan.mappers.MedicalDetailMapper;
import com.temirlan.mappers.PatientRowMapper;
import com.temirlan.models.BlogPost;
import com.temirlan.models.MedicalCard;
import com.temirlan.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BlogPostRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addBlogPost(BlogPost post) {
        String sql = "INSERT INTO BlogPosts (doctorid, title, content, photo_url, post_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getDoctorId(), post.getTitle(), post.getContent(), post.getPhotoUrl(), post.getPostDate());
    }

    public List<BlogPost> listPosts(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM blogposts WHERE doctorid = ?",
                new Object[]{id},
                new BlogPostsRowMapper()
        );
    }

    public List<BlogPost> lists3() {
        return jdbcTemplate.query(
                "SELECT * FROM blogposts LIMIT 3",
                new BlogPostsRowMapper()
        );
    }

    public List<BlogPost> lists() {
        return jdbcTemplate.query(
                "SELECT * FROM blogposts",
                new BlogPostsRowMapper()
        );
    }

    public BlogPost findById(int id) {
        String sql = "SELECT * FROM BlogPosts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BlogPostsRowMapper());
    }

    public void updateBlogPost(BlogPost post) {
        String sql = "UPDATE BlogPosts SET title = ?, content = ?, photo_url = ?, post_date = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getPhotoUrl(), post.getPostDate(), post.getId());
    }
}
