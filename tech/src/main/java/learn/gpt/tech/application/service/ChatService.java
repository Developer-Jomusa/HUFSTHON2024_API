package learn.gpt.tech.application.service;


import learn.gpt.tech.domain.ChatMessage;
import learn.gpt.tech.domain.ChatRoom;
import learn.gpt.tech.dto.ChatMessageDto;
import learn.gpt.tech.dto.ChatRoomDto;
import learn.gpt.tech.dto.request.ChatRoomDeleteServiceRequest;
import learn.gpt.tech.dto.request.ChatRoomDetailServiceRequest;
import learn.gpt.tech.dto.request.CreateChatRoomServiceRequest;
import learn.gpt.tech.dto.response.CreateChatRoomResponse;
import learn.gpt.tech.dto.response.DetailChatResponse;
import learn.gpt.tech.infra.repository.ChatMessageJpaRepository;
import learn.gpt.tech.infra.repository.ChatRoomJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private static final String WEB_SOCKET_URL = "ws://yourserver.com/ws/chat";

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatMessageJpaRepository chatMessageJpaRepository;

    @Transactional
    public CreateChatRoomResponse create(CreateChatRoomServiceRequest request) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.of(roomId, request.getRoomName());
        ChatRoom savedRoom = chatRoomJpaRepository.save(chatRoom);
        String webSocketUrl = String.format("ws://localhost:8080/ws/chat?roomId=%s", savedRoom.getRoomId());
        log.info("Created and connected to new room: {}", chatRoom.getRoomName());
        return new CreateChatRoomResponse(roomId, request.getUsername(), webSocketUrl);
    }

    @Transactional(readOnly = true)
    public DetailChatResponse detail(ChatRoomDetailServiceRequest serviceRequest) {

        ChatRoom chatRoom = chatRoomJpaRepository.findById(serviceRequest.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 방입니다."));
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom.getRoomId());

        String webSocketUrl = String.format("ws://localhost:8080/ws/chat?roomId=%s", chatRoom.getRoomId());

        List<ChatMessage> chatMessageBy = chatMessageJpaRepository.findByRoomId(chatRoom);
        List<ChatMessageDto> chatList = chatMessageBy.stream()
                .map(chatMessage -> new ChatMessageDto(chatMessage.getUserId(), chatMessage.getMessage(), chatMessage.getSenderType()))
                .toList();

        return new DetailChatResponse(webSocketUrl, chatRoomDto, chatList);
    }

    @Transactional
    public void delete(ChatRoomDeleteServiceRequest serviceRequest) {
        ChatRoom chatRoom = chatRoomJpaRepository.findById(serviceRequest.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 방입니다."));
        List<ChatMessage> chatMessageBy = chatMessageJpaRepository.findByRoomId(chatRoom);
        chatRoomJpaRepository.delete(chatRoom);
        chatMessageJpaRepository.deleteAll(chatMessageBy);
    }
}
