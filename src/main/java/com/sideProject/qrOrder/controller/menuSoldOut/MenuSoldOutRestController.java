package com.sideProject.qrOrder.controller.menuSoldOut;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.dto.menuSoldOut.MenuSoldOutDto;
import com.sideProject.qrOrder.service.menuOptionSoldOut.MenuOptionSoldOutService;
import com.sideProject.qrOrder.service.menuSoldOut.MenuSoldOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu/sold-out")
@RequiredArgsConstructor
public class MenuSoldOutRestController {

    private final MenuSoldOutService menuSoldOutService;

    @PostMapping("/create")
    public ApiResponse<String> createMenuSoldOut(@RequestBody MenuSoldOutDto requestDto) {

        menuSoldOutService.createMenuSoldOut(requestDto);

        return ApiResponse.ok("메뉴가 품절처리 되었습니다");
    }

    @PutMapping("/modify/sold-out")
    public ApiResponse<String> modifyMenuStatusToSoldOut(@RequestBody MenuSoldOutDto requestDto) {

        menuSoldOutService.createMenuSoldOut(requestDto);

        return ApiResponse.ok("메뉴가 품절처리 되었습니다");
    }

    @DeleteMapping("/delete/{msId}")
    public ApiResponse<String> deleteMenuSoldOut(@PathVariable Long msId) {

        menuSoldOutService.deleteMenuSoldOut(msId);

        return ApiResponse.ok("메뉴가 품절취소 되었습니다");
    }
}
