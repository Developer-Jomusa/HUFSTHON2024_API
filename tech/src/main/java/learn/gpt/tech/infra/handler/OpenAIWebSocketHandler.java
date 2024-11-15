package learn.gpt.tech.infra.handler;

import learn.gpt.tech.domain.ChatMessage;
import learn.gpt.tech.domain.ChatRoom;
import learn.gpt.tech.infra.repository.ChatMessageJpaRepository;
import learn.gpt.tech.infra.repository.ChatRoomJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class OpenAIWebSocketHandler extends TextWebSocketHandler {

    private final OpenAiChatModel chatModel;
    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatMessageJpaRepository chatMessageJpaRepository;

    @Autowired
    public OpenAIWebSocketHandler(OpenAiChatModel chatModel, ChatRoomJpaRepository chatRoomJpaRepository, ChatMessageJpaRepository chatMessageJpaRepository) {
        this.chatModel = chatModel;
        this.chatRoomJpaRepository = chatRoomJpaRepository;
        this.chatMessageJpaRepository = chatMessageJpaRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String roomId = UriComponentsBuilder.fromUri(session.getUri())
                .build()
                .getQueryParams()
                .getFirst("roomId");

        String username = UriComponentsBuilder.fromUri(session.getUri())
                .build()
                .getQueryParams()
                .getFirst("username");


        String encodeUsername = URLDecoder.decode(username, StandardCharsets.UTF_8);

        session.getAttributes().put("roomId", roomId);
        session.getAttributes().put("username", encodeUsername);
        log.info("Connected to room:  {}", roomId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String roomId = UriComponentsBuilder.fromUri(session.getUri())
                .build()
                .getQueryParams()
                .getFirst("roomId");
        String username = (String) session.getAttributes().get("username");


        ChatRoom chatRoom = chatRoomJpaRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 방 입니다."));

        UserMessage userMessages = new UserMessage(message.getPayload());

        ChatMessage userChat = ChatMessage.userOf(chatRoom, username, userMessages.getContent());
        chatMessageJpaRepository.save(userChat);

//        ChatResponse response = chatModel.call(new Prompt(userMessages));
//        String aiResponse = response.getResults().getFirst().getOutput().getContent();
        String formattedMessage = String.format("그러시군요 %s님! 제 답변은 %s", username, "aiResponse");
        ChatMessage aiChat = ChatMessage.aiOf(chatRoom, username, "aiResponse");
        chatMessageJpaRepository.save(aiChat);

        TextMessage textMessage = new TextMessage(formattedMessage);
        session.sendMessage(textMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport error", exception);
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = UriComponentsBuilder.fromUri(session.getUri())
                .build()
                .getQueryParams()
                .getFirst("roomId");

        super.afterConnectionClosed(session, status);
    }
}
