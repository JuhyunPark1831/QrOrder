package com.sideProject.qrOrder.repository.category;

import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryCustomRepository {

    public Page<CategoryResponseDto> findCategoryDtoList(Pageable pageable, CategoryRequestDto requestDto);
    public void shiftDownCaSeq(List<Long> caIds);
}
