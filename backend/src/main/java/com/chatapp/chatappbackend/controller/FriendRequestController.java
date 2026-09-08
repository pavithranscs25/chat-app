package com.chatapp.chatappbackend.controller;

import com.chatapp.chatappbackend.entity.FriendRequest;
import com.chatapp.chatappbackend.service.FriendRequestService;
import org.springframework.web.bind.annotation.*;
import com.chatapp.chatappbackend.entity.User;

import java.util.List;

@RestController
@RequestMapping("/api/friend-requests")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping
    public FriendRequest sendRequest(
            @RequestParam Integer senderId,
            @RequestParam Integer receiverId) {

        return friendRequestService.sendRequest(senderId, receiverId);
    }

    @PutMapping("/{requestId}")
    public FriendRequest updateRequestStatus(
            @PathVariable Integer requestId,
            @RequestParam FriendRequest.Status status) {

        return friendRequestService.updateRequestStatus(requestId, status);
    }

    @GetMapping("/pending/{receiverId}")
    public List<FriendRequest> getPendingRequests(
            @PathVariable Integer receiverId) {

        return friendRequestService.getPendingRequests(receiverId);
    }

    @GetMapping("/accepted/{userId}")
    public List<User> getAcceptedFriends(
            @PathVariable Integer userId) {

        return friendRequestService.getAcceptedFriends(userId);
    }

    @GetMapping("/sent/{senderId}")
    public List<FriendRequest> getSentRequests(
            @PathVariable Integer senderId) {

        return friendRequestService.getSentRequests(senderId);
    }
}