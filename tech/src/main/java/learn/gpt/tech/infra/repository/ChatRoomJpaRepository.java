package learn.gpt.tech.infra.repository;

import learn.gpt.tech.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoom, String> {
}
