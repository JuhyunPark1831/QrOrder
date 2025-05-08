package com.sideProject.qrOrder.controller.account;

import com.sideProject.qrOrder.dto.account.AccountRequestDto;
import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/login")
    public String loginPage() {
        return "/manager/pages/account/login";
    }

    @GetMapping("/create")
    public String createAccountPage() {
        return "/manager/pages/account/createAccount";
    }

    @GetMapping("/manage")
    public String manageAccountPage(Model model,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<AccountResponseDto> accountResponseDtoPage = accountService.selectAccount(pageable, null);

        model.addAttribute("accountList", accountResponseDtoPage);
        model.addAttribute("currentPage", accountResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", accountResponseDtoPage.getTotalPages());

        return "/manager/pages/account/manageAccount";
    }

    @PostMapping("/replace/manage/search")
    public String manageAccountPageSearch(Model model,
                                          @PageableDefault(page = 0, size = 10) Pageable pageable,
                                          @RequestBody AccountRequestDto requestDto) {

        Page<AccountResponseDto> accountResponseDtoPage = accountService.selectAccount(pageable, requestDto);

        model.addAttribute("accountList", accountResponseDtoPage);
        model.addAttribute("currentPage", accountResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", accountResponseDtoPage.getTotalPages());

        return "/manager/pages/account/manageAccount :: #account-list";
    }
}
