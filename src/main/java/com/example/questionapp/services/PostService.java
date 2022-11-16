package com.example.questionapp.services;

import com.example.questionapp.entities.Post;
import com.example.questionapp.entities.User;
import com.example.questionapp.repos.PostRepository;
import com.example.questionapp.requests.PostCreateRequest;
import com.example.questionapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById( postId ).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());
        if(user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(  toSave );
    }

    public Post updateOnePostById(Long postId , PostUpdateRequest updateRequest) {

        Optional<Post> optionalPost = postRepository.findById( postId );
        if(optionalPost.isPresent()){
            Post toUpdate = optionalPost.get();
            toUpdate.setText(updateRequest.getText());
            toUpdate.setTitle(updateRequest.getTitle());
            postRepository.save( toUpdate );
            return toUpdate;

        }
        return null;



    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById( postId );
    }
}
