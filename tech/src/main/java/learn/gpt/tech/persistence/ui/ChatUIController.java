package learn.gpt.tech.persistence.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat")
public class ChatUIController {

    @GetMapping("/chats")
    public String chat() {
        return "chat";
    }
}
