package com.sideProject.qrOrder.controller.manageSoldOut;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sold-out")
@RequiredArgsConstructor
public class ManageSoldOutController {

    @GetMapping("/manage/menu-option")
    public String manageMenuOptionSoldOutPage() {
        return "/manager/pages/manageSoldOut/manageMenuOptionSoldOut";
    }

    @GetMapping("/manage/menu")
    public String manageMenuSoldOutPage() {
        return "/manager/pages/manageSoldOut/manageMenuSoldOut";
    }
}
