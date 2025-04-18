package com.sideProject.qrOrder.service.category;

import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    public void createCategory(CategoryRequestDto requestDto);
    public Page<CategoryResponseDto> selectCategory(Pageable pageable, CategoryRequestDto requestDto);
    public void modifyCategoryName(CategoryRequestDto requestDto);
    public void modifyCategorySeq(List<CategoryRequestDto> requestDtoList);
    public void deleteCategory(List<Long> caIds);
    public void checkCategory(CategoryRequestDto requestDto);
}
