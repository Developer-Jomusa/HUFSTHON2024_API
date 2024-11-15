package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class ChatRoomDeleteServiceRequest {
    private final String roomId;

    private ChatRoomDeleteServiceRequest(String roomId) {
        this.roomId = roomId;
    }

    public static ChatRoomDeleteServiceRequest toServiceRequest(ChatRoomDeleteRequest request){
        return new ChatRoomDeleteServiceRequest(request.getRoomId());
    }
}
