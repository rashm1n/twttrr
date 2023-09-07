package com.rashm1n.twtr.likeservice.controller;

import com.rashm1n.twtr.likeservice.model.Like;
import com.rashm1n.twtr.likeservice.model.LikeDTO;
import com.rashm1n.twtr.likeservice.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    public LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody LikeDTO likeDTO) {
        return ResponseEntity.ok(likeService.createLike(likeDTO));
    }

    @DeleteMapping("/{tweet_id}")
    public ResponseEntity<Void> deleteLike(@PathVariable String tweet_id,@RequestParam String user, @RequestParam boolean isRetweet) {
        likeService.deleteLike(tweet_id, user, isRetweet);
        return ResponseEntity.ok().build();
    }
}
