package com.sideProject.qrOrder.repository.closing;

import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingResponseDto;
import com.sideProject.qrOrder.entity.Closing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClosingCustomRepository {

    public Page<Closing> findClosing(Pageable pageable, ClosingRequestDto requestDto);
}
