package top.ksmdev.exam2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ksmdev.exam2025.config.ConfigProperties;


@RestController
public class IndexController {
    @Autowired
    ConfigProperties configProperties;

    @GetMapping("/")
    public String index() {
        return "UP";
    }

    @GetMapping("/wrong")
    public int wrong() {
        return 9 / 0;
    }

}
