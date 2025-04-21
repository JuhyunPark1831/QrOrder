package com.sideProject.qrOrder.controller.closingFixed;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/closing-fixed")
@RequiredArgsConstructor
public class ClosingFixedController {

    @GetMapping("/manage")
    public String manageClosingFixedPage() {
        return "/manager/pages/closing/manageClosingFixed";
    }
}
