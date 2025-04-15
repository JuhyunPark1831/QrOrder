package com.sideProject.qrOrder.controller.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    @GetMapping("/create")
    public String createMenuPage() {
        return "/manager/pages/menu/createMenu";
    }

    @GetMapping("/manage")
    public String manageMenuPage() {
        return "/manager/pages/menu/manageMenu";
    }

    @GetMapping("/modify")
    public String modifyMenuPage() {
        return "/manager/pages/menu/modifyMenu";
    }
}
