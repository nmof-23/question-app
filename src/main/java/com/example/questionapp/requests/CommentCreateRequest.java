package com.example.questionapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

    private Long id;
    private String text;
    private Long userId;
    private Long postId;

}
