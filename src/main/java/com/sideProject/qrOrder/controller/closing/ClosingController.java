package com.sideProject.qrOrder.controller.closing;

import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingResponseDto;
import com.sideProject.qrOrder.service.closing.ClosingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/closing")
@RequiredArgsConstructor
public class ClosingController {

    private final ClosingService closingService;

    @GetMapping("/manage")
    public String manageClosingPage(Model model,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<ClosingResponseDto> closingResponseDtoPage = closingService.selectClosing(pageable, ClosingRequestDto.builder()
                .clStartSearch(LocalDate.now())
                .clEndSearch(LocalDate.now().plusYears(1))
                .build());

        model.addAttribute("closingList", closingResponseDtoPage);
        model.addAttribute("currentPage", closingResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", closingResponseDtoPage.getTotalPages());

        return "/manager/pages/closing/manageClosing";
    }

    @PostMapping("/replace/manage/search")
    public String manageClosingPageSearch(Model model,
                                          @PageableDefault(page = 0, size = 10) Pageable pageable,
                                          @RequestBody ClosingRequestDto requestDto) {

        Page<ClosingResponseDto> closingResponseDtoPage = closingService.selectClosing(pageable, requestDto);

        model.addAttribute("closingList", closingResponseDtoPage);
        model.addAttribute("currentPage", closingResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", closingResponseDtoPage.getTotalPages());

        return "/manager/pages/closing/manageClosing :: #closing-list";
    }
}
