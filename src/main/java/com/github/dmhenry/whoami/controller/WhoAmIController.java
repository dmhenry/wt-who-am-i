package com.github.dmhenry.whoami.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1.0/")
public class WhoAmIController {

    @ResponseBody
    @PostMapping("new")
    String home() {
        return "Hello World!";
    }

}
