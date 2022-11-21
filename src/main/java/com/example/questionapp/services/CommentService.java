package com.example.questionapp.services;

import com.example.questionapp.entities.Comment;
import com.example.questionapp.entities.Post;
import com.example.questionapp.entities.User;
import com.example.questionapp.repos.CommentRepository;
import com.example.questionapp.requests.CommentCreateRequest;
import com.example.questionapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
        //        if (userId.isPresent() ) {
//            if(postId.isPresent()){
//                return commentRepository.findByPostId(postId.get());
//            }
//
//        }

        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get() , postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return  commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    public Comment getOneCommentByUserId(Long userId) {
        return commentRepository.findById( userId ).orElse(null);
    }

    public Comment getOneCommentByPostId(Long postId) {
        return commentRepository.findById( postId ).orElse(null);
    }

    public Comment getOneCommentByCommentId(Long commentId) {
        return commentRepository.findById( commentId ).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUser(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());

        if(user == null || post == null){
            return  null;
        }

        Comment toSave = new Comment();
        toSave.setId(commentCreateRequest.getId());
        toSave.setText(commentCreateRequest.getText());
        toSave.setUser(user);
        toSave.setPost(post);
        return commentRepository.save(toSave);

    }

    public Comment updateOneComment(CommentUpdateRequest commentUpdateRequest, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById( commentId );
        if(commentOptional.isPresent()){
            Comment toUpdate = commentOptional.get();
            toUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(toUpdate);
        }
        return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById( commentId );
    }


}
