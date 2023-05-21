package com.rashm1n.twtr.timelineservice.controller;

import com.rashm1n.twtr.timelineservice.dao.TimelineDTO;
import com.rashm1n.twtr.timelineservice.dto.TimelineEntryDTO;
import com.rashm1n.twtr.timelineservice.service.TimelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineService timelineService;

    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @PostMapping
    public ResponseEntity<TimelineEntryDTO> insertTimelineEntry(@RequestBody TimelineEntryDTO timelineEntryDTO) {
        return ResponseEntity.ok(timelineService.insertTimelineEntry(timelineEntryDTO));
    }

    @DeleteMapping("/{user_id}/{tweet_id}")
    public ResponseEntity<Boolean> deleteTimelineEntry(@PathVariable String user_id, @PathVariable String tweet_id) {
        return ResponseEntity.ok(timelineService.deleteTimelineEntry(user_id, tweet_id));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<TimelineDTO> getTimeline(@PathVariable String user_id) {
        return ResponseEntity.ok(timelineService.getTimeline(user_id));
    }
}
