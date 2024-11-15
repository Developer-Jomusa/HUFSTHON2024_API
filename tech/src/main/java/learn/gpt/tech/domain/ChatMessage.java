package learn.gpt.tech.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom roomId;

    private String userId;

    private String message;

    private String senderType;

    private LocalDateTime sentAt;


    private ChatMessage(ChatRoom roomId, String userId, String message, String senderType) {
        this.roomId = roomId;
        this.userId = userId;
        this.message = message;
        this.senderType = senderType;
        this.sentAt = LocalDateTime.now();
    }

    public static ChatMessage userOf(ChatRoom roomId, String userId, String message) {
        return new ChatMessage(roomId, userId, message, "user");
    }

    public static ChatMessage aiOf(ChatRoom roomId, String userId, String message) {
        return new ChatMessage(roomId, userId, message, "ai");
    }
}
