package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class CreateChatRoomRequest {

    private String roomName;
    private String username;
}
