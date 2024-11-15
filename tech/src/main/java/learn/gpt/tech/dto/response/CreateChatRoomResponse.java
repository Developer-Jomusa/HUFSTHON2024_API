package learn.gpt.tech.dto.response;

import lombok.Getter;

@Getter
public class CreateChatRoomResponse {

    private final String roomId;
    private final String username;
    private final String webSocketUrl;

    public CreateChatRoomResponse(String roomId, String username, String webSocketUrl) {
        this.roomId = roomId;
        this.username = username;
        this.webSocketUrl = webSocketUrl;
    }
}
