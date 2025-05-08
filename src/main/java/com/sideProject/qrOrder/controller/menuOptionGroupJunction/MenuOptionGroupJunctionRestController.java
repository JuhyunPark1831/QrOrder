package com.sideProject.qrOrder.controller.menuOptionGroupJunction;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.service.menuOptionGroupJunction.MenuOptionGroupJunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-option-group-junction")
@RequiredArgsConstructor
public class MenuOptionGroupJunctionRestController {

    private final MenuOptionGroupJunctionService menuOptionGroupJunctionService;

    @PostMapping("/find/menu-name")
    public ApiResponse<List<String>> findMenuNameByMenuOptionGroup(@RequestBody MenuOptionGroupDto requestDto) {
        return ApiResponse.ok(menuOptionGroupJunctionService.findMenuByMenuOptionGroup(requestDto.getOgId()));
    }
}
