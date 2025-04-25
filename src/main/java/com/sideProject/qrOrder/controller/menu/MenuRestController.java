package com.sideProject.qrOrder.controller.menu;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    @PostMapping("/create")
    public ApiResponse<String> createMenu(@ModelAttribute MenuDto requestDto) {

        menuService.createMenu(requestDto);

        return ApiResponse.ok("메뉴 등록이 완료되었습니다");
    }
}
