package com.rashm1n.twtr.followerservice.controller;

import com.rashm1n.twtr.followerservice.model.FollowerDTO;
import com.rashm1n.twtr.followerservice.model.UserFollowersDTO;
import com.rashm1n.twtr.followerservice.service.FollowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/followers")
public class FollowerController {
    private final FollowerService followerService;

    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserFollowersDTO> getFollowers(@PathVariable String user_id) {
        return ResponseEntity.ok(followerService.getFollowers(user_id));
    }

    @PostMapping
    public ResponseEntity<FollowerDTO> addFollower(@RequestBody FollowerDTO followerDTO) {
        return ResponseEntity.ok(followerService.insertFollower(followerDTO));
    }

    @DeleteMapping("/{user_id}/{follower_id}")
    public ResponseEntity<Boolean> deleteFollower(@PathVariable String user_id, @PathVariable String follower_id) {
        return ResponseEntity.ok(followerService.deleteFollower(user_id, follower_id));
    }
}
