//package com.rashm1n.twtr.followerservice.consumer;
//
//import com.rashm1n.twtr.followerservice.model.FollowerDTO;
//import com.rashm1n.twtr.followerservice.model.UserFollowersDTO;
//import com.rashm1n.twtr.followerservice.model.message.UserFollowerMessageDTO;
//import com.rashm1n.twtr.followerservice.service.FollowerService;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserFollowersListener {
//
//    private final FollowerService followerService;
//
//    public UserFollowersListener(FollowerService followerService) {
//        this.followerService = followerService;
//    }
//
//    @KafkaListener(topics = "user_followers", groupId = "follower-service")
//    public void consumeUserFollowers(UserFollowerMessageDTO userFollowerMessageDTO) {
//        FollowerDTO followerDTO = new FollowerDTO(userFollowerMessageDTO.getUser_id(), userFollowerMessageDTO.getFollower_id());
//        if (userFollowerMessageDTO.getAction() == UserFollowerMessageDTO.Action.DELETE)
//            followerService.deleteFollower(followerDTO.getUser_id(), followerDTO.getFollower_id());
//        else if (userFollowerMessageDTO.getAction() == UserFollowerMessageDTO.Action.ADD) {
//            followerService.insertFollower(followerDTO);
//        }
//    }
//}
