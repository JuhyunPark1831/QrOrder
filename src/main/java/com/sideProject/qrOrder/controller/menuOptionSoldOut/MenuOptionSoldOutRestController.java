package com.sideProject.qrOrder.controller.menuOptionSoldOut;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menuOptionSoldOut.MenuOptionSoldOutDto;
import com.sideProject.qrOrder.service.menuOptionSoldOut.MenuOptionSoldOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-option/sold-out")
@RequiredArgsConstructor
public class MenuOptionSoldOutRestController {

    private final MenuOptionSoldOutService menuOptionSoldOutService;

    @PostMapping("/create")
    public ApiResponse<String> createMenuOptionSoldOut(@RequestBody MenuOptionSoldOutDto requestDto) {

        menuOptionSoldOutService.createMenuOptionSoldOut(requestDto);

        return ApiResponse.ok("옵션이 품절처리 되었습니다");
    }

    @DeleteMapping("/delete/{ogId}")
    public ApiResponse<String> deleteMenuOptionSoldOut(@PathVariable Long ogId) {

        menuOptionSoldOutService.deleteMenuOptionSoldOut(ogId);

        return ApiResponse.ok("옵션이 품절취소 되었습니다");
    }
}
