package learn.gpt.tech.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    private String roomId;

    private String roomName;

    private LocalDateTime createdAt;

    private ChatRoom(String roomId, String roomName, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.createdAt = createdAt;
    }

    public static ChatRoom of(String uuid,String roomId){
        return new ChatRoom(uuid, roomId, LocalDateTime.now());
    }
}
