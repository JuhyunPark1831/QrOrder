package com.sideProject.qrOrder.controller.menu;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menu.MenuDto;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    @PostMapping("/create")
    public ApiResponse<String> createMenu(@ModelAttribute MenuDto requestDto) {

        menuService.createMenu(requestDto);

        return ApiResponse.ok("메뉴가 등록되었습니다");
    }

    @PutMapping("/modify")
    public ApiResponse<String> modifyMenu(@ModelAttribute MenuDto requestDto) {

        menuService.modifyMenu(requestDto);

        return ApiResponse.ok("메뉴가 수정되었습니다");
    }

    @PutMapping("/modify/available/{meId}")
    public ApiResponse<String> modifyMenuStatusToAvailable(@PathVariable Long meId) {

        menuService.modifyMeStatus(meId, SoldOutStatus.AVAILABLE);

        return ApiResponse.ok("메뉴가 판매중처리 되었습니다");
    }

    @PutMapping("/modify/hidden/{meId}")
    public ApiResponse<String> modifyMenuStatusToHidden(@PathVariable Long meId) {

        menuService.modifyMeStatus(meId, SoldOutStatus.HIDDEN);

        return ApiResponse.ok("메뉴가 숨김처리 되었습니다");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteMenu(@RequestBody MenuDto requestDto) {

        menuService.deleteMenu(requestDto);

        return ApiResponse.ok("메뉴가 삭제되었습니다");
    }
}
