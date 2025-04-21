package com.sideProject.qrOrder.service.closing;

import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClosingService {

    public void createClosing(ClosingRequestDto requestDto);
    public Page<ClosingResponseDto> selectClosing(Pageable pageable, ClosingRequestDto requestDto);
    public void deleteClosing(List<Long> clIds);
}
