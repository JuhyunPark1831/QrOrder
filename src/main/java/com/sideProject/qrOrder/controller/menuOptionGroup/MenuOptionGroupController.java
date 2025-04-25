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
    public String createMenuOptionPage() {
        return "/manager/pages/menuOptionGroup/createMenuOptionGroup";
    }

    @GetMapping("/manage")
    public String manageMenuOptionPage(@PageableDefault(page = 0, size = 3) Pageable pageable, Model model) {

        Page<MenuOptionGroupDto> menuOptionGroupResponseDtoPage = menuOptionGroupService.selectMenuOptionGroup(pageable, null);
        model.addAttribute("menuOptionGroupList", menuOptionGroupResponseDtoPage);
        model.addAttribute("currentPage", menuOptionGroupResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", menuOptionGroupResponseDtoPage.getTotalPages());
        
        return "/manager/pages/menuOptionGroup/manageMenuOptionGroup";
    }

    @PostMapping("/replace/manage/search")
    public String manageMenuOptionPageSearch(@PageableDefault(page = 0, size = 3) Pageable pageable, @RequestBody MenuOptionGroupDto requestDto, Model model) {

        Page<MenuOptionGroupDto> menuOptionGroupResponseDtoPage = menuOptionGroupService.selectMenuOptionGroup(pageable, requestDto);
        model.addAttribute("menuOptionGroupList", menuOptionGroupResponseDtoPage);
        model.addAttribute("currentPage", menuOptionGroupResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", menuOptionGroupResponseDtoPage.getTotalPages());

        return "/manager/fragments/menuOptionGroup/menuOptionGroupFragment :: menu-option-group-fragment";
    }

    @GetMapping("/modify/{ogId}")
    public String modifyMenuOptionPage(@PathVariable Long ogId, Model model) {

        model.addAttribute("menuOptionDetail", menuOptionGroupService.selectMenuOptionGroupDetail(ogId));

        return "/manager/pages/menuOptionGroup/modifyMenuOptionGroup";
    }
}
