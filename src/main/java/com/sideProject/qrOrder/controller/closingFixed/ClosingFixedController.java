package com.sideProject.qrOrder.controller.closingFixed;

import com.sideProject.qrOrder.dto.closingFixed.ClosingFixedDto;
import com.sideProject.qrOrder.service.closingFixed.ClosingFixedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/closing-fixed")
@RequiredArgsConstructor
public class ClosingFixedController {

    private final ClosingFixedService closingFixedService;

    @GetMapping("/manage")
    public String manageClosingFixedPage(Model model,
                                         @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<ClosingFixedDto> closingFixedDtoPage = closingFixedService.selectClosingFixed(pageable);

        model.addAttribute("closingFixedList", closingFixedDtoPage);
        model.addAttribute("currentPage", closingFixedDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", closingFixedDtoPage.getTotalPages());

        return "/manager/pages/closing/manageClosingFixed";
    }

    @GetMapping("/replace/manage/search")
    public String manageClosingFixedPageSearch(Model model,
                                               @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<ClosingFixedDto> closingFixedDtoPage = closingFixedService.selectClosingFixed(pageable);

        model.addAttribute("closingFixedList", closingFixedDtoPage);
        model.addAttribute("currentPage", closingFixedDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", closingFixedDtoPage.getTotalPages());

        return "/manager/pages/closing/manageClosingFixed :: #closing-fixed-list";
    }
}
