package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class ChatRoomDetailServiceRequest {
    private String roomId;
    private String username;

    private ChatRoomDetailServiceRequest(String roomId,String username) {
        this.roomId = roomId;
        this.username = username;
    }


    public static ChatRoomDetailServiceRequest toServiceRequest(ChatRoomDetailRequest request){
        return new ChatRoomDetailServiceRequest(request.getRoomId(),request.getUsername());
    }
}
