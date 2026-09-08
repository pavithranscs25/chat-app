package com.chatapp.chatappbackend.service;

import com.chatapp.chatappbackend.entity.FriendRequest;
import com.chatapp.chatappbackend.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;
import com.chatapp.chatappbackend.repository.UserRepository;
import com.chatapp.chatappbackend.entity.User;
import java.util.List;

@Service
public class FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public FriendRequestService(FriendRequestRepository friendRequestRepository,
                                UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    public FriendRequest sendRequest(Integer senderId, Integer receiverId) {

        boolean exists = friendRequestRepository
                .existsBySenderIdAndReceiverIdAndStatus(
                        senderId,
                        receiverId,
                        FriendRequest.Status.PENDING
                );

        if (exists) {
            throw new RuntimeException("Friend request already sent");
        }

        FriendRequest request = new FriendRequest(
                senderId,
                receiverId,
                FriendRequest.Status.PENDING
        );

        return friendRequestRepository.save(request);
    }

    public FriendRequest updateRequestStatus(
            Integer requestId,
            FriendRequest.Status status
    ) {
        FriendRequest request = friendRequestRepository
                .findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        request.setStatus(status);

        return friendRequestRepository.save(request);
    }

    public List<FriendRequest> getPendingRequests(Integer receiverId) {
        return friendRequestRepository.findByReceiverIdAndStatus(
                receiverId,
                FriendRequest.Status.PENDING
        );
    }

    public List<User> getAcceptedFriends(Integer userId) {

        List<FriendRequest> requests =
                friendRequestRepository
                        .findBySenderIdAndStatusOrReceiverIdAndStatus(
                                userId,
                                FriendRequest.Status.ACCEPTED,
                                userId,
                                FriendRequest.Status.ACCEPTED
                        );

        return requests.stream()
                .map(request -> {

                    Integer friendId =
                            request.getSenderId().equals(userId)
                                    ? request.getReceiverId()
                                    : request.getSenderId();

                    return userRepository.findById(friendId)
                            .orElseThrow(() ->
                                    new RuntimeException("Friend not found"));
                })
                .toList();
    }

    public List<FriendRequest> getSentRequests(Integer senderId) {
        return friendRequestRepository.findBySenderIdAndStatus(
                senderId,
                FriendRequest.Status.PENDING
        );
    }
}