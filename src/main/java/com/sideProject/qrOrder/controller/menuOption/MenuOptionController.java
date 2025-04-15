package com.sideProject.qrOrder.controller.menuOption;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu-option")
@RequiredArgsConstructor
public class MenuOptionController {

    @GetMapping("/create")
    public String createMenuOptionPage() {
        return "/manager/pages/menuOption/createMenuOption";
    }

    @GetMapping("/manage")
    public String manageMenuOptionPage() {
        return "/manager/pages/menuOption/manageMenuOption";
    }

    @GetMapping("/modify")
    public String modifyMenuOptionPage() {
        return "/manager/pages/menuOption/modifyMenuOption";
    }
}
