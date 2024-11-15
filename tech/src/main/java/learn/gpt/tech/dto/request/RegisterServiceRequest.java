package learn.gpt.tech.dto.request;

import lombok.Getter;

@Getter
public class RegisterServiceRequest {

    private String name;

    private String password;

    private RegisterServiceRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static RegisterServiceRequest toServiceRequest(RegisterRequest request) {
        return new RegisterServiceRequest(request.getName(), request.getPassword());
    }

}
