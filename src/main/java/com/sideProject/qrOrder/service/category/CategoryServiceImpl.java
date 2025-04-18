package com.sideProject.qrOrder.service.category;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Category;
import com.sideProject.qrOrder.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createCategory(CategoryRequestDto requestDto) {

        checkCategory(requestDto);

        int caSeq = categoryRepository.findTopByOrderByCaSeqDesc()
                .map(Category::getCaSeq)
                .orElse(0) + 1;

        categoryRepository.save(Category.builder()
                        .caName(requestDto.getCaName())
                        .caSeq(caSeq)
                .build());
    }

    @Override
    public Page<CategoryResponseDto> selectCategory(Pageable pageable, CategoryRequestDto requestDto) {

        return categoryRepository.findCategory(pageable, requestDto);
    }

    @Override
    @Transactional
    public void modifyCategoryName(CategoryRequestDto requestDto) {

        checkCategory(requestDto);

        Category category = categoryRepository.findById(requestDto.getCaId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_CATEGORY));

        category.modifyCaName(requestDto.getCaName());
    }

    @Override
    @Transactional
    public void modifyCategorySeq(List<CategoryRequestDto> requestDtoList) {

        for (CategoryRequestDto requestDto : requestDtoList) {
            categoryRepository.findById(requestDto.getCaId())
                    .ifPresent(category -> {
                        category.modifyCaSeq(requestDto.getCaSeq());
                    });
        }
    }

    @Override
    @Transactional
    public void deleteCategory(List<Long> caIds) {

        categoryRepository.shiftDownCaSeq(caIds);

        categoryRepository.deleteAllById(caIds);
    }

    @Override
    public void checkCategory(CategoryRequestDto requestDto) {
        if (categoryRepository.findByCaName(requestDto.getCaName()).isPresent()) {
            throw new ApiCustomException(ErrorCode.NOT_UNIQUE_CATEGORY_NAME);
        }
    }
}
