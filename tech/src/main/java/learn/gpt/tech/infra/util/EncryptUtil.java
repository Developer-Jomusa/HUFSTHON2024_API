package learn.gpt.tech.infra.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    public static String encryptSHA256(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 암호화 알고리즘을 찾을 수 없습니다.", e);
        }
    }
}
