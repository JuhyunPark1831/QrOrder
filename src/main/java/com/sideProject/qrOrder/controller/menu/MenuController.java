package com.sideProject.qrOrder.controller.menu;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.menu.MenuService;
import com.sideProject.qrOrder.service.menuOptionGroup.MenuOptionGroupService;
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
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final CategoryService categoryService;
    private final MenuOptionGroupService menuOptionGroupService;
    private final MenuService menuService;

    @GetMapping("/create")
    public String createMenuPage(Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));
        model.addAttribute("menuOptionGroupList", menuOptionGroupService.selectMenuOptionGroup(Pageable.unpaged(), null));

        return "/manager/pages/menu/createMenu";
    }

    @GetMapping("/manage")
    public String manageMenuPage(Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));

        Page<MenuDto> menuResponseDtoPage = menuService.selectMenu(Pageable.unpaged(), null);
        model.addAttribute("menuList", menuResponseDtoPage);

        return "/manager/pages/menu/manageMenu";
    }

    @PostMapping("/replace/manage/search")
    public String manageMenuPageSearch(@RequestBody MenuDto requestDto, Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));

        Page<MenuDto> menuResponseDtoPage = menuService.selectMenu(Pageable.unpaged(), requestDto);
        model.addAttribute("menuList", menuResponseDtoPage);

        return "/manager/pages/menu/manageMenu :: #menu-list";
    }

    @GetMapping("/modify")
    public String modifyMenuPage() {
        return "/manager/pages/menu/modifyMenu";
    }
}
