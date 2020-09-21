package ru.Mikhail.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.Mikhail.domain.Message;
import ru.Mikhail.repos.MessageRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    private MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {

        findAllAndPutMessagesToModel(model);
        return "main";
    }

    private void findAllAndPutMessagesToModel(Map<String, Object> model) {
        model.put("messages", messageRepo.findAll());
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

        Message message = new Message(text, tag);
        messageRepo.save(message);

        findAllAndPutMessagesToModel(model);
        return "main";
    }

    @GetMapping("filter")
    public String filter(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;
        if (tag == null || tag.isEmpty())
            messages = messageRepo.findAll();
        else
            messages = messageRepo.findByTag(tag);

        model.put("messages", messages);

        return "main";
    }
}
