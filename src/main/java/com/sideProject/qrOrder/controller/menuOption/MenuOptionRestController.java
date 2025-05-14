package com.sideProject.qrOrder.controller.menuOption;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.entity.Common.ENUM.SoldOutStatus;
import com.sideProject.qrOrder.service.menuOption.MenuOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-option")
@RequiredArgsConstructor
public class MenuOptionRestController {

    private final MenuOptionService menuOptionService;

    @PutMapping("/modify/available/{opId}")
    public ApiResponse<String> modifyMenuOptionStatusToAvailable(@PathVariable Long opId) {

        menuOptionService.modifyOpStatus(opId, SoldOutStatus.AVAILABLE);

        return ApiResponse.ok("메뉴가 판매중처리 되었습니다");
    }

    @PutMapping("/modify/hidden/{opId}")
    public ApiResponse<String> modifyMenuStatusOptionToHidden(@PathVariable Long opId) {

        menuOptionService.modifyOpStatus(opId, SoldOutStatus.HIDDEN);

        return ApiResponse.ok("메뉴가 숨김처리 되었습니다");
    }
}
