package learn.gpt.tech.infra.repository;

import learn.gpt.tech.domain.ChatMessage;
import learn.gpt.tech.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomId(ChatRoom chatRoom);
}
