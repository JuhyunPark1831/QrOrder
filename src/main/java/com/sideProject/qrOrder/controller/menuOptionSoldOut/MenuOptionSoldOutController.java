package com.sideProject.qrOrder.controller.menuOptionSoldOut;

import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutResponseDto;
import com.sideProject.qrOrder.service.menuOptionSoldOut.MenuOptionSoldOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu-option/sold-out")
@RequiredArgsConstructor
public class MenuOptionSoldOutController {

    private final MenuOptionSoldOutService menuOptionSoldOutService;

    @GetMapping("/manage")
    public String manageMenuOptionSoldOutPage(Model model,
                                              @PageableDefault(page = 0, size = 3) Pageable pageable) {

        model.addAttribute("menuOptionGroupList", menuOptionSoldOutService.selectMenuOptionSoldOut(pageable, null));

        return "/manager/pages/menuOptionSoldOut/manageMenuOptionSoldOut";
    }

    @PostMapping("/replace/manage/search")
    public String manageMenuOptionSoldOutPageSearch(Model model,
                                                    @PageableDefault(page = 0, size = 3) Pageable pageable,
                                                    @RequestBody MenuOptionSoldOutDto requestDto) {

        model.addAttribute("menuOptionGroupList", menuOptionSoldOutService.selectMenuOptionSoldOut(pageable, requestDto));

        return "/manager/fragments/menuOptionSoldOut/menuOptionSoldOutFragment :: menu-option-soldout-fragment";
    }
}
