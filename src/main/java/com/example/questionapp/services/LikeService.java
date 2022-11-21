package com.example.questionapp.services;

import com.example.questionapp.entities.Like;
import com.example.questionapp.entities.Post;
import com.example.questionapp.entities.User;
import com.example.questionapp.repos.LikeRepository;
import com.example.questionapp.requests.CreateLikeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get() , postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else
            return likeRepository.findAll();
    }

    public Like getOneLike(Long likeId) {
        return likeRepository.findById( likeId ).orElse(null);
    }


    public Like createOneLike(CreateLikeRequest createLikeRequest) {
        User user = userService.getOneUser(createLikeRequest.getUserId());
        Post post = postService.getOnePostById(createLikeRequest.getPostId());

        if(user != null && post != null){
            Like toCreate = new Like();
            toCreate.setId(createLikeRequest.getId());
            toCreate.setUser(user);
            toCreate.setPost(post);
            return likeRepository.save(toCreate);
        } else {
            return null;
        }

    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById( likeId );
    }
}
