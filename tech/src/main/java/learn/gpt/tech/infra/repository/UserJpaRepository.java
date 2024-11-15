package learn.gpt.tech.infra.repository;

import learn.gpt.tech.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByNameAndPassword(String username, String password);
}
