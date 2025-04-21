package com.sideProject.qrOrder.service.closing;

import com.sideProject.qrOrder.dto.account.AccountResponseDto;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.category.CategoryResponseDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingResponseDto;
import com.sideProject.qrOrder.entity.Account;
import com.sideProject.qrOrder.entity.Closing;
import com.sideProject.qrOrder.repository.closing.ClosingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClosingServiceImpl implements ClosingService {

    private final ClosingRepository closingRepository;

    @Override
    @Transactional
    public void createClosing(ClosingRequestDto requestDto) {

        closingRepository.save(Closing.builder()
                        .clStart(requestDto.getClStart())
                        .clEnd(requestDto.getClEnd())
                .build());
    }

    @Override
    public Page<ClosingResponseDto> selectClosing(Pageable pageable, ClosingRequestDto requestDto) {

        Page<Closing> closingPage = closingRepository.findClosing(pageable, requestDto);

        return closingPage.map(ClosingResponseDto :: from);
    }

    @Override
    @Transactional
    public void deleteClosing(List<Long> clIds) {

        closingRepository.deleteAllById(clIds);
    }
}
