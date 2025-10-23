package top.ksmdev.exam2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "UP";
    }

    @GetMapping("/wrong")
    public int wrong() {
        return 9 / 0;
    }

}
