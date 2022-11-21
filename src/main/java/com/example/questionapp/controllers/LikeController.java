package com.example.questionapp.controllers;

import com.example.questionapp.entities.Like;
import com.example.questionapp.requests.CreateLikeRequest;
import com.example.questionapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional<Long> userId , @RequestParam Optional<Long> postId){
        return likeService.getAllLikes( userId , postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLike( likeId );
    }

    @PostMapping
    public Like createOneLike(@RequestBody CreateLikeRequest createLikeRequest){
        return likeService.createOneLike( createLikeRequest );
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLike( likeId );
    }



}
