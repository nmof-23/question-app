package com.example.questionapp.repos;

import com.example.questionapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like , Long> {
}
