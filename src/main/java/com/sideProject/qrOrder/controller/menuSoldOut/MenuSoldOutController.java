package com.sideProject.qrOrder.controller.menuSoldOut;

import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutResponseDto;
import com.sideProject.qrOrder.dto.menuSoldOut.MenuSoldOutDto;
import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.menuOptionSoldOut.MenuOptionSoldOutService;
import com.sideProject.qrOrder.service.menuSoldOut.MenuSoldOutService;
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
@RequestMapping("/menu/sold-out")
@RequiredArgsConstructor
public class MenuSoldOutController {

    private final CategoryService categoryService;
    private final MenuSoldOutService menuSoldOutService;

    @GetMapping("/manage")
    public String manageMenuSoldOutPage(Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));

        model.addAttribute("menuSoldOutList", menuSoldOutService.selectMenuSoldOut(Pageable.unpaged(), null));

        return "/manager/pages/menuSoldOut/manageMenuSoldOut";
    }

    @PostMapping("/replace/manage/search")
    public String manageMenuSoldOutPageSearch(Model model,
                                              @RequestBody MenuSoldOutDto requestDto) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));

        model.addAttribute("menuSoldOutList", menuSoldOutService.selectMenuSoldOut(Pageable.unpaged(), requestDto));

        return "/manager/pages/menuSoldOut/manageMenuSoldOut :: #menu-sold-out-list";
    }
}
