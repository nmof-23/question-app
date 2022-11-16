package com.example.questionapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    private Long id;

    private Long userId;
    private Long postId;

    @Lob
    @Column(columnDefinition = "text")
    private String text;
}
