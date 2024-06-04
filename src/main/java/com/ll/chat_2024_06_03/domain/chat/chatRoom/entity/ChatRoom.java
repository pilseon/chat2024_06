package com.ll.chat_2024_06_03.domain.chat.chatRoom.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor(access = PROTECTED) // 생성자 (접근제어자 : PROTECTED)
@NoArgsConstructor(access = PROTECTED) // 생성자x (접근제어자 : PROTECTED)
public class ChatRoom {

    @Id
    @Getter
    @GeneratedValue(strategy = IDENTITY) // PRIMARY_KEY
    private long id;

    @CreatedDate
    @Getter
    private LocalDateTime createDate;

    @LastModifiedDate
    @Getter
    private LocalDateTime modifyDate;

    @Getter
    private String name;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @Getter
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatRoom(String name){
        this.name = name;
    }

    public void writeMessage(String writerName, String content) {
        ChatMessage chatMessage = ChatMessage
                .builder()
                .chatRoom(this)
                .writerName(writerName)
                .content(content)
                .build();
        chatMessages.add(chatMessage);
    }
}
