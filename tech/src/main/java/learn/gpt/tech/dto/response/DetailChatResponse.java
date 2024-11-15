package learn.gpt.tech.dto.response;

import learn.gpt.tech.dto.ChatMessageDto;
import learn.gpt.tech.dto.ChatRoomDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailChatResponse {

    private final String webSocketUrl;
    private final ChatRoomDto chatRoom;
    private final List<ChatMessageDto> chatMessages;

    public DetailChatResponse(String webSocketUrl, ChatRoomDto chatRoom, List<ChatMessageDto> chatMessages) {
        this.webSocketUrl = webSocketUrl;
        this.chatRoom = chatRoom;
        this.chatMessages = chatMessages;
    }
}
