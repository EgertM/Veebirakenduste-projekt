package com.veebirakendus.Attempt1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "Hello, World!";
    }
}
