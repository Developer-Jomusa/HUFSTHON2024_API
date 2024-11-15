package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class CreateChatRoomServiceRequest {

    private String roomName;
    private String username;


    private CreateChatRoomServiceRequest(String roomName, String username) {
        this.roomName = roomName;
        this.username = username;
    }

    public static CreateChatRoomServiceRequest toServiceRequest(CreateChatRoomRequest request) {
        return new CreateChatRoomServiceRequest(request.getRoomName(), request.getUsername());
    }
}
