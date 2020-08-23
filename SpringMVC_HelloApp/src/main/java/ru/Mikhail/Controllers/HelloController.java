package ru.Mikhail.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping(path = { "/", "/hello" })
    public String Hello() {
        // согласно настройкам applicationContextMVC.xml должно вернуть /WEB-INF/views/hello.html
        // но почему-то возвращает /resources/templates//hello.html
        return "hello";
    }
}
