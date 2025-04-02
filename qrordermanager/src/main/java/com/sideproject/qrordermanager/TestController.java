package com.sideproject.qrordermanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "account/manageAccount";
    }

    @GetMapping("/2")
    public String test1() {
        return "account/createAccount";
    }
}
