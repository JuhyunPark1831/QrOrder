package com.sideProject.qrOrder.controller.client;

import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final CategoryService categoryService;
    private final MenuService menuService;

    @GetMapping("/menuList")
    public String menuListPage(Model model) {

        model.addAttribute("categoryList", categoryService.selectCategory(Pageable.unpaged(), null));

        model.addAttribute("menuList", menuService.selectMenu(Pageable.unpaged(), MenuDto.builder()
                .meStatus(SoldOutStatus.HIDDEN)
                .build()));

        model.addAttribute("menuDetail", null);

        return "/client/pages/menuList";
    }

    @GetMapping("/replace/menu/pop/{meId}")
    public String menuPopOpen(Model model,
                               @PathVariable Long meId) {

        model.addAttribute("menuDetail", menuService.selectMenuDetailClient(meId));

        return "/client/pages/menuList :: #menu-detail-pop";
    }

    @GetMapping("/orderList")
    public String orderListPage() {
        return "/client/pages/orderList";
    }
}

//todo: 메뉴 관리 페이지 메뉴 상태 체크
//todo: 메뉴 옵션에서 최소 1개 이상 선택해야되는 옵션 그룹일 때 품절 못하게 처리