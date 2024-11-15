package learn.gpt.tech.dto;

import lombok.Getter;

@Getter
public class ChatMessageDto {

    private String userId;

    private String message;

    private String senderType;

    public ChatMessageDto(String userId, String message, String senderType) {
        this.userId = userId;
        this.message = message;
        this.senderType = senderType;
    }
}
