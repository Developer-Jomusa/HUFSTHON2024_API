package learn.gpt.tech.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_users")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private Users(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public static Users create(String name, String password) {
        return new Users(name, password);
    }

    public void checkPassword(String encryptedPassword) {
        boolean isEqualsPassword = password.equals(encryptedPassword);
        if(!isEqualsPassword){
            throw new IllegalArgumentException("올바르지 않은 패스워드입니다.");
        }
    }
}
