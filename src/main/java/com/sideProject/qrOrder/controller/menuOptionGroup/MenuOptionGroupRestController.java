package com.sideProject.qrOrder.controller.menuOptionGroup;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.service.menuOptionGroup.MenuOptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-option-group")
@RequiredArgsConstructor
public class MenuOptionGroupRestController {

    private final MenuOptionGroupService menuOptionGroupService;

    @PostMapping("/create")
    public ApiResponse<String> createMenuOptionGroup(@RequestBody MenuOptionGroupDto requestDto) {

        menuOptionGroupService.createMenuOptionGroup(requestDto);

        return ApiResponse.ok("메뉴 옵션이 등록 되었습니다");
    }

    @PutMapping("/modify")
    public ApiResponse<String> modifyMenuOptionGroup(@RequestBody MenuOptionGroupDto requestDto) {

        menuOptionGroupService.modifyMenuOptionGroup(requestDto);

        return ApiResponse.ok("메뉴 옵션이 수정 되었습니다");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteMenuOptionGroup(@RequestBody MenuOptionGroupDto requestDto) {

        menuOptionGroupService.deleteMenuOptionGroup(requestDto);

        return ApiResponse.ok("메뉴 옵션이 삭제되었습니다");
    }
}
