package com.github.dmhenry.whoami.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1.0/who-am-i")
public class WhoAmIController {

    @RequestMapping("/new")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
