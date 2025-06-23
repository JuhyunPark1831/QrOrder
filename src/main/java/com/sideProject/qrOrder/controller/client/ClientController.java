package com.sideProject.qrOrder.controller.client;

import com.sideProject.qrOrder.dto.client.CartInfoRequestDto;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final CategoryService categoryService;
    private final MenuService menuService;

    @GetMapping
    public String index() {
        return "client/pages/index";
    }

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

    @PostMapping("/replace/order-list")
    public String orderListPageFragment(Model model,
                                        @RequestBody List<CartInfoRequestDto> requestDtoList) {

        model.addAttribute("orderList", menuService.getCartInfo(requestDtoList));

        return "/client/fragments/orderListFragment :: order-list-fragment";
    }

    @GetMapping("/closing")
    public String closingPage() {
        return "/client/pages/closing";
    }
}