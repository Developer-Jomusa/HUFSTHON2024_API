package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class ChatRoomDetailServiceRequest {
    private String roomId;

    private ChatRoomDetailServiceRequest(String roomId) {
        this.roomId = roomId;
    }


    public static ChatRoomDetailServiceRequest toServiceRequest(ChatRoomDetailRequest request){
        return new ChatRoomDetailServiceRequest(request.getRoomId());
    }
}
