package com.example.questionapp.controllers;

import com.example.questionapp.entities.Comment;
import com.example.questionapp.requests.CommentCreateRequest;
import com.example.questionapp.requests.CommentUpdateRequest;
import com.example.questionapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId , @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId , postId);
    }



//    @GetMapping("/{userId}")
//    public Comment getOneCommentByUserId(@PathVariable Long userId){
//        return commentService.getOneCommentByUserId( userId );
//    }
//
//    @GetMapping("/{postId}")
//    public Comment getOneCommentByPostId(@PathVariable Long postId){
//        return commentService.getOneCommentByPostId( postId );
//    }

    @GetMapping("/{commentId}")
    public Comment getOneCommentByCommentId(@PathVariable Long commentId){
        return commentService.getOneCommentByCommentId( commentId );
    }

    @PostMapping()
    public Comment createOneComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createOneComment( commentCreateRequest );
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@RequestBody CommentUpdateRequest commentUpdateRequest , @PathVariable Long commentId){
        return commentService.updateOneComment( commentUpdateRequest , commentId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment( commentId );
    }




}
