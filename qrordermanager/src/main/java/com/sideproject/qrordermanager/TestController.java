package com.sideproject.qrordermanager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping
    public String test() {
        return "account/manageAccount";
    }
}
