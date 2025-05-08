package com.sideProject.qrOrder.repository.closing;

import com.sideProject.qrOrder.dto.closing.ClosingDto;
import com.sideProject.qrOrder.entity.Closing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClosingCustomRepository {

    public Page<Closing> findClosing(Pageable pageable, ClosingDto requestDto);
}
