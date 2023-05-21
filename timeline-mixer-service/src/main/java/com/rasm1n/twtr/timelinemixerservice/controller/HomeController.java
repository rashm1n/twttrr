package com.rasm1n.twtr.timelinemixerservice.controller;

import com.rasm1n.twtr.timelinemixerservice.service.HomeService;
import com.rasm1n.twtr.timelinemixerservice.model.FeedDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseEntity<FeedDTO> getHomeFeed(@RequestParam String userId) {
        return ResponseEntity.ok(homeService.getHomeFeed(userId));
    }
}
