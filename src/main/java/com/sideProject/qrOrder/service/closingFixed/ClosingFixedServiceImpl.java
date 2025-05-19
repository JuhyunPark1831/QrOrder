package com.sideProject.qrOrder.service.closingFixed;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import com.sideProject.qrOrder.dto.closingFixed.ClosingFixedDto;
import com.sideProject.qrOrder.entity.Category;
import com.sideProject.qrOrder.entity.ClosingFixed;
import com.sideProject.qrOrder.entity.Common.ENUM.UseStatus;
import com.sideProject.qrOrder.repository.closingFixed.ClosingFixedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClosingFixedServiceImpl implements ClosingFixedService {

    private final ClosingFixedRepository closingFixedRepository;

    @Override
    @Transactional
    public void createClosingFixed(ClosingFixedDto requestDto) {
        closingFixedRepository.save(ClosingFixed.builder()
                        .cfWeekNum(requestDto.getCfWeekNum())
                        .cfWeekDay(requestDto.getCfWeekDay())
                        .cfStartTime(requestDto.getCfStartTime())
                        .cfEndTime(requestDto.getCfEndTime())
                        .cfUseStatus(UseStatus.USING)
                .build());
    }

    @Override
    public Page<ClosingFixedDto> selectClosingFixed(Pageable pageable) {

        Page<ClosingFixed> closingFixedPage = closingFixedRepository.findAll(pageable);

        return closingFixedPage.map(ClosingFixedDto :: from);
    }

    @Override
    @Transactional
    public void modifyClosingFixedUseStatus(ClosingFixedDto requestDto) {

        ClosingFixed closingFixed = closingFixedRepository.findById(requestDto.getCfId()).orElseThrow(() ->
                new ApiCustomException(ErrorCode.NOT_FOUND_CLOSING_FIXED));

        closingFixed.toggleUseStatus();
    }

    @Override
    @Transactional
    public void deleteClosingFixed(List<Long> cfIds) {
        closingFixedRepository.deleteAllById(cfIds);
    }

    @Override
    public boolean checkNowIsClosingFixed() {
        return closingFixedRepository.findClosingFixedByDateTimeBetweenStartAndEnd(LocalDateTime.now()).isPresent();
    }
}
