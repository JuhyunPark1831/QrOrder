package com.sideProject.qrOrder.controller.menu;

import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.menuOptionGroup.MenuOptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final CategoryService categoryService;
    private final MenuOptionGroupService menuOptionGroupService;

    @GetMapping("/create")
    public String createMenuPage(Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));
        model.addAttribute("menuOptionGroupList", menuOptionGroupService.selectMenuOptionGroup(Pageable.unpaged(), null));

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
