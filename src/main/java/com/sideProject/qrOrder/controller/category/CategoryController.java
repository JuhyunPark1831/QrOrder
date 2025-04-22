package com.sideProject.qrOrder.controller.category;

import com.sideProject.qrOrder.dto.account.AccountRequestDto;
import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.service.category.CategoryService;
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

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/manage")
    public String manageCategoryPage(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model) {

        Page<CategoryResponseDto> categoryResponseDtoPage = categoryService.selectCategory(pageable, null);
        model.addAttribute("categoryList", categoryResponseDtoPage);
        model.addAttribute("currentPage", categoryResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", categoryResponseDtoPage.getTotalPages());

        return "/manager/pages/category/manageCategory";
    }

    @PostMapping("/replace/manage/search")
    public String manageCategoryPageSearch(@PageableDefault(page = 0, size = 10) Pageable pageable, @RequestBody CategoryRequestDto requestDto, Model model) {

        Page<CategoryResponseDto> categoryResponseDtoPage = categoryService.selectCategory(pageable, requestDto);
        model.addAttribute("categoryList", categoryResponseDtoPage);
        model.addAttribute("currentPage", categoryResponseDtoPage.getPageable().getPageNumber());
        model.addAttribute("totalPage", categoryResponseDtoPage.getTotalPages());

        return "/manager/pages/category/manageCategory :: #category-list";
    }
}
