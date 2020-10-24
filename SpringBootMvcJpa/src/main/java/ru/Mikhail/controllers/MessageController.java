package ru.Mikhail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.Mikhail.domain.*;
import ru.Mikhail.repos.MessageRepo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MessageController {

    private final MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam MultipartFile file,
            Model model) throws IOException {

        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
        messageRepo.save(message);

        model.addAttribute("messages", messageRepo.findAll());
        return "main";
    }
}
