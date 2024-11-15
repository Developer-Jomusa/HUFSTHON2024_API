package learn.gpt.tech.persistence.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatUIController {

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
