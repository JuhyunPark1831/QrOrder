package com.sideProject.qrOrder.controller.category;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ApiResponse<String> createCategory(@RequestBody CategoryRequestDto requestDto) {

        categoryService.createCategory(requestDto);

        return ApiResponse.ok("카테고리가 등록 되었습니다");
    }

    @PutMapping("/modify")
    public ApiResponse<String> modifyCategoryName(@RequestBody CategoryRequestDto requestDto) {

        categoryService.modifyCategoryName(requestDto);

        return ApiResponse.ok("카테고리 이름이 변경되었습니다");
    }

    @PutMapping("/modify/order")
    public ApiResponse<String> modifyCategorySeq(@RequestBody List<CategoryRequestDto> reorderedList) {

        categoryService.modifyCategorySeq(reorderedList);

        return ApiResponse.ok("카테고리 순서가 변경되었습니다");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteCategory(@RequestBody List<Long> caIds) {

        categoryService.deleteCategory(caIds);

        return ApiResponse.ok("카테고리가 삭제되었습니다");
    }
}
