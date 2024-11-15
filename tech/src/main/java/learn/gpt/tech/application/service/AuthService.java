package learn.gpt.tech.application.service;

import learn.gpt.tech.domain.Users;
import learn.gpt.tech.dto.request.LoginServiceRequest;
import learn.gpt.tech.dto.request.RegisterServiceRequest;
import learn.gpt.tech.dto.response.LoginResponse;
import learn.gpt.tech.infra.repository.UserJpaRepository;
import learn.gpt.tech.infra.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;

    public void registerUser(RegisterServiceRequest serviceRequest) {
        Users users = Users.create(serviceRequest.getName(), EncryptUtil.encryptSHA256(serviceRequest.getPassword()));
        userJpaRepository.save(users);
    }

    public LoginResponse authLogin(LoginServiceRequest serviceRequest) {
        String encryptedPassword = EncryptUtil.encryptSHA256(serviceRequest.getPassword());
        Users users = userJpaRepository.findByNameAndPassword(serviceRequest.getName(), encryptedPassword)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자입니다."));
        users.checkPassword(encryptedPassword);
        return new LoginResponse(users.getName());
    }
}
