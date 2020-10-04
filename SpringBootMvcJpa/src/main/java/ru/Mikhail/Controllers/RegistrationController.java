package ru.Mikhail.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.Mikhail.domain.Role;
import ru.Mikhail.domain.User;
import ru.Mikhail.repos.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private UserRepo userRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        if (user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {

            model.put("message", "ERROR! Username and password cannot be empty!");
            return "registration";
        }
        User existUser = userRepo.findByUsername(user.getUsername());
        if (existUser != null) {
            model.put("message", "ERROR! User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
