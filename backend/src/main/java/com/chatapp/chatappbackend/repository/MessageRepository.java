package com.chatapp.chatappbackend.repository;

import com.chatapp.chatappbackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findBySenderIdAndReceiverIdOrSenderIdAndReceiverId(
            Integer senderId1,
            Integer receiverId1,
            Integer senderId2,
            Integer receiverId2
    );
}
