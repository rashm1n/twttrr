package com.rashm1n.twtr.timelineservice.service;

import com.rashm1n.twtr.timelineservice.dao.TimelineDTO;
import com.rashm1n.twtr.timelineservice.dao.TimelineDao;
import com.rashm1n.twtr.timelineservice.dto.TimelineEntryDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;

@Service
public class TimelineService {
    private final TimelineDao timelineDao;

    public TimelineService(TimelineDao timelineDao) {
        this.timelineDao = timelineDao;
    }

    public TimelineEntryDTO insertTimelineEntry(TimelineEntryDTO timelineEntryDTO) {
        String key = createKey(timelineEntryDTO.getUser_id());
        boolean success = timelineDao.insertTimelineEntry(
                key,
                timelineEntryDTO.getTweet_id(),
                Double.parseDouble(timelineEntryDTO.getTimestamp()));
        if (success) {
            System.out.println("Inserted timeline entry: " + timelineEntryDTO);
            return timelineEntryDTO;
        }
        return null;
    }

    public boolean deleteTimelineEntry(String user_id, String tweet_id) {
        String key = createKey(user_id);
        return timelineDao.deleteTimelineEntry(key, tweet_id);
    }

    public TimelineDTO getTimeline(String user_id) {
        String key = createKey(user_id);
        LinkedHashSet<String> set = timelineDao.getTimeline(key);
        return new TimelineDTO(user_id, set);
    }

    private String createKey(String user_id) {
        return "timeline:" + user_id;
    }
}
