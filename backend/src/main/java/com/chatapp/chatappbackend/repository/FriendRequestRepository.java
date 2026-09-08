package com.chatapp.chatappbackend.repository;

import com.chatapp.chatappbackend.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository
        extends JpaRepository<FriendRequest, Integer> {

    boolean existsBySenderIdAndReceiverIdAndStatus(
            Integer senderId,
            Integer receiverId,
            FriendRequest.Status status
    );

    List<FriendRequest> findByReceiverIdAndStatus(
            Integer receiverId,
            FriendRequest.Status status
    );

    List<FriendRequest> findBySenderIdAndStatus(
            Integer senderId,
            FriendRequest.Status status
    );

    List<FriendRequest> findBySenderIdAndStatusOrReceiverIdAndStatus(
            Integer senderId,
            FriendRequest.Status senderStatus,
            Integer receiverId,
            FriendRequest.Status receiverStatus
    );
}