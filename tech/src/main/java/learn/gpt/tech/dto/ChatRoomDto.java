package learn.gpt.tech.dto;

import lombok.Getter;

@Getter
public class ChatRoomDto {

    private String roomId;

    public ChatRoomDto(String roomId) {
        this.roomId = roomId;
    }
}
