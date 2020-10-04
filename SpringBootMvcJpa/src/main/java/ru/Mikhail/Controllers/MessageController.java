package ru.Mikhail.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Mikhail.domain.*;
import ru.Mikhail.repos.MessageRepo;

@Controller
public class MessageController {

    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String tag, Model model) {

        Iterable<Message> messages;
        if (tag == null || tag.isEmpty())
            messages = messageRepo.findAll();
        else
            messages = messageRepo.findByTag(tag);

        model.addAttribute("messages", messages);
        model.addAttribute("tag", tag);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model) {

        Message message = new Message(text, tag, user);
        messageRepo.save(message);

        model.addAttribute("messages", messageRepo.findAll());
        return "main";
    }
}
