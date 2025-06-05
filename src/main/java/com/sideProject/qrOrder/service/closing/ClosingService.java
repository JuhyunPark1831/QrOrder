package com.sideProject.qrOrder.service.closing;

import com.sideProject.qrOrder.dto.closing.ClosingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClosingService {

    public void createClosing(ClosingDto requestDto);
    public Page<ClosingDto> selectClosing(Pageable pageable, ClosingDto requestDto);
    public void deleteClosing(List<Long> clIds);
    public boolean checkNowIsClosing();
}
