package learn.gpt.tech.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private String username;

    public LoginResponse(String username) {
        this.username = username;
    }
}
