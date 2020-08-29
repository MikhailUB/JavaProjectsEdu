package ru.Mikhail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping(path = { "/", "/helloWorld"})
    public String Hello() {

        return "helloworld";
    }
}
