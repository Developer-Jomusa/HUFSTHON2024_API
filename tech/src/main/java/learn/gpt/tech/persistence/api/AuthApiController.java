package learn.gpt.tech.persistence.api;

import jakarta.validation.Valid;
import learn.gpt.tech.application.service.AuthService;
import learn.gpt.tech.dto.request.LoginRequest;
import learn.gpt.tech.dto.request.LoginServiceRequest;
import learn.gpt.tech.dto.request.RegisterRequest;
import learn.gpt.tech.dto.request.RegisterServiceRequest;
import learn.gpt.tech.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(RegisterServiceRequest.toServiceRequest(request));
    }

    @PostMapping("/api/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
       return authService.authLogin(LoginServiceRequest.toServiceRequest(request));
    }

}
