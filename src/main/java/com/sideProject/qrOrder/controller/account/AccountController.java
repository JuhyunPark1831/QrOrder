package com.sideProject.qrOrder.controller.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    @GetMapping("/login")
    public String loginPage() {
        return "/manager/pages/account/login";
    }

    @GetMapping("/create")
    public String createAccountPage() {
        return "/manager/pages/account/createAccount";
    }

    @GetMapping("/manage")
    public String manageAccountPage() {
        return "/manager/pages/account/manageAccount";
    }
}
