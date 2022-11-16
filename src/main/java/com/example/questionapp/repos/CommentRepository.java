package com.example.questionapp.repos;

import com.example.questionapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
