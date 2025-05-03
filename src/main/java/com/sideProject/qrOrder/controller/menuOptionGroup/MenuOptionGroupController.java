package com.sideProject.qrOrder.controller.menuOptionGroup;

import com.sideProject.qrOrder.dto.menuOptionGroup.MenuOptionGroupDto;
import com.sideProject.qrOrder.service.menuOptionGroup.MenuOptionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu-option-group")
@RequiredArgsConstructor
public class MenuOptionGroupController {

    private final MenuOptionGroupService menuOptionGroupService;

    @GetMapping("/create")
    public String createMenuOptionGroupPage() {
        return "/manager/pages/menuOptionGroup/createMenuOptionGroup";
    }

    @GetMapping("/modify/{ogId}")
    public String modifyMenuOptionGroupPage(Model model,
                                       @PathVariable Long ogId) {

        model.addAttribute("menuOptionDetail", menuOptionGroupService.selectMenuOptionGroupDetail(ogId));

        return "/manager/pages/menuOptionGroup/modifyMenuOptionGroup";
    }

    @GetMapping("/manage")
    public String manageMenuOptionGroupPage(Model model,
                                            @PageableDefault(page = 0, size = 3) Pageable pageable) {

        Page<MenuOptionGroupDto> menuOptionGroupResponseDtoPage = menuOptionGroupService.selectMenuOptionGroup(pageable, null);

        model.addAttribute("menuOptionGroupList", menuOptionGroupResponseDtoPage);
        
        return "/manager/pages/menuOptionGroup/manageMenuOptionGroup";
    }

    @PostMapping("/replace/manage/search")
    public String manageMenuOptionGroupPageSearch(Model model,
                                             @PageableDefault(page = 0, size = 3) Pageable pageable,
                                             @RequestBody MenuOptionGroupDto requestDto) {

        Page<MenuOptionGroupDto> menuOptionGroupResponseDtoPage = menuOptionGroupService.selectMenuOptionGroup(pageable, requestDto);

        model.addAttribute("menuOptionGroupList", menuOptionGroupResponseDtoPage);

        return "/manager/fragments/menuOptionGroup/menuOptionGroupFragment :: menu-option-group-fragment";
    }
}
