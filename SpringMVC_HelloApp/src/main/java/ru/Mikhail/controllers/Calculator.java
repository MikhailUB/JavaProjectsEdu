package ru.Mikhail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Calculator {

    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "arg1", defaultValue = "0") double arg1,
                          @RequestParam(value = "arg2", defaultValue = "0") double arg2,
                          @RequestParam(value = "operation", defaultValue = "") String operation,
                          Model model) {
        double result = 0;
        String error = "";
        switch (operation) {
            case "plus":
                result = arg1 + arg2;
                break;
            case "minus":
                result = arg1 - arg2;
                break;
            case "multiply":
                result = arg1 * arg2;
                break;
            case "division":
                if (arg2 != 0)
                    result = arg1 / arg2;
                else
                    error = "нельзя делить на ноль";
                break;
        }
        model.addAttribute("result", result);
        model.addAttribute("error", error);
        return "calculator";
    }
}
