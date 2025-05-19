package com.sideProject.qrOrder.repository.closingFixed;

import com.sideProject.qrOrder.entity.ClosingFixed;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ClosingFixedCustomRepository {
    public Optional<ClosingFixed> findClosingFixedByDateTimeBetweenStartAndEnd(LocalDateTime dateTime);
}
