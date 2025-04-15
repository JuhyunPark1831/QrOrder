package com.sideProject.qrOrder.controller.closing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/closing")
@RequiredArgsConstructor
public class ClosingController {

    @GetMapping("/manage")
    public String manageClosingPage() {
        return "/manager/pages/closing/manageClosing";
    }

    @GetMapping("/manage/fixed")
    public String manageClosingFixedPage() {
        return "/manager/pages/closing/manageClosingFixed";
    }
}
