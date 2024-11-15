package learn.gpt.tech.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginServiceRequest {

    @NotNull(message = "사용자 이름을 입력하지 않았습니다.")
    private String name;

    @NotNull(message = "비밀번호를 입력하지 않았습니다.")
    private String password;

    public LoginServiceRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static LoginServiceRequest toServiceRequest(LoginRequest request) {
        return new LoginServiceRequest(request.getName(), request.getPassword());
    }
}
