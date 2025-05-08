package com.sideProject.qrOrder.service.closingFixed;

import com.sideProject.qrOrder.dto.closingFixed.ClosingFixedDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClosingFixedService {

    public void createClosingFixed(ClosingFixedDto requestDto);
    public Page<ClosingFixedDto> selectClosingFixed(Pageable pageable);
    public void modifyClosingFixedUseStatus(ClosingFixedDto requestDto);
    public void deleteClosingFixed(List<Long> cfIds);
}
