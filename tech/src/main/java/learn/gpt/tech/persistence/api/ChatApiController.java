package learn.gpt.tech.persistence.api;

import learn.gpt.tech.application.service.ChatService;
import learn.gpt.tech.dto.request.*;
import learn.gpt.tech.dto.response.CreateChatRoomResponse;
import learn.gpt.tech.dto.response.DetailChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatApiController {

    private final ChatService chatService;

    @PostMapping("/api/create")
    public ResponseEntity<CreateChatRoomResponse> createChatRoom(@RequestBody CreateChatRoomRequest request){
        return ResponseEntity.ok(chatService.create(CreateChatRoomServiceRequest.toServiceRequest(request)));
    }

    @PostMapping("/api/detail")
    public ResponseEntity<DetailChatResponse> detailChatRoom(@RequestBody ChatRoomDetailRequest request) {
        return ResponseEntity.ok(chatService.detail(ChatRoomDetailServiceRequest.toServiceRequest(request)));
    }


    @PostMapping("/api/delete")
    public void deleteChatRoom(@RequestBody ChatRoomDeleteRequest request) {
        chatService.delete(ChatRoomDeleteServiceRequest.toServiceRequest(request));
    }
}
