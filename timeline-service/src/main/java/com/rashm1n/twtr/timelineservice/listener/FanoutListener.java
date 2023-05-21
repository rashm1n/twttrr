package com.rashm1n.twtr.timelineservice.listener;

import com.rashm1n.twtr.timelineservice.dto.TimelineEntryDTO;
import com.rashm1n.twtr.timelineservice.model.message.FanOutMessageDTO;
import com.rashm1n.twtr.timelineservice.service.TimelineService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutListener {
    @Value("${TIMELINE_SERVER_ID}")
    private int serverId;
    final TimelineService timelineService;

    public FanoutListener(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @KafkaListener(topics = "twtrr-fanout", groupId = "timeline-service")
    public void consumePost(FanOutMessageDTO fanOutMessageDTO) {
        System.out.println("Received Message in server " + serverId);

        TimelineEntryDTO timelineEntryDTO = new TimelineEntryDTO();
        timelineEntryDTO.setTweet_id(fanOutMessageDTO.getTweet_id());
        timelineEntryDTO.setTimestamp(String.valueOf(fanOutMessageDTO.getTimestamp().getEpochSecond()));
        timelineEntryDTO.setUser_id(fanOutMessageDTO.getUser_id());

        if (fanOutMessageDTO.getAction() == FanOutMessageDTO.Action.CREATED)
            timelineService.insertTimelineEntry(timelineEntryDTO);
        else if (fanOutMessageDTO.getAction() == FanOutMessageDTO.Action.DELETED)
            timelineService.deleteTimelineEntry(timelineEntryDTO.getUser_id(), timelineEntryDTO.getTweet_id());
    }
}
