package com.rasm1n.twtr.timelinemixerservice.controller;

import com.rasm1n.twtr.timelinemixerservice.model.FeedDTO;
import com.rasm1n.twtr.timelinemixerservice.service.UserHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-home")
public class UserHomeController {
    private final UserHomeService userHomeService;

    public UserHomeController(UserHomeService userHomeService) {
        this.userHomeService = userHomeService;
    }

    @GetMapping
    @RequestMapping("/{userId}")
    public ResponseEntity<FeedDTO> getUserHomeFeed(@PathVariable String userId) {
        return ResponseEntity.ok(userHomeService.getUserFeed(userId));
    }
}
