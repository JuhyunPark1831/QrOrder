package com.sideProject.qrOrder.controller.menuOptionGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu-option-group")
@RequiredArgsConstructor
public class MenuOptionGroupController {

    @GetMapping("/create")
    public String createMenuOptionPage() {
        return "/manager/pages/menuOptionGroup/createMenuOptionGroup";
    }

    @GetMapping("/manage")
    public String manageMenuOptionPage() {
        return "/manager/pages/menuOptionGroup/manageMenuOptionGroup";
    }

    @GetMapping("/modify")
    public String modifyMenuOptionPage() {
        return "/manager/pages/menuOptionGroup/modifyMenuOptionGroup";
    }
}
