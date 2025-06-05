package com.sideProject.qrOrder.repository.closingFixed;

import com.sideProject.qrOrder.entity.ClosingFixed;
import com.sideProject.qrOrder.entity.Common.ENUM.WeekDay;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ClosingFixedCustomRepository {
    public Optional<ClosingFixed> findClosingFixedByDateTimeBetweenStartAndEnd(int cfWeekNum, WeekDay cfWeekDay);
}
