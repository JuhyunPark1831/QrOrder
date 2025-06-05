package com.sideProject.qrOrder.repository.closingFixed;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sideProject.qrOrder.entity.ClosingFixed;
import com.sideProject.qrOrder.entity.Common.ENUM.UseStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.WeekDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static com.sideProject.qrOrder.entity.QClosing.closing;
import static com.sideProject.qrOrder.entity.QClosingFixed.closingFixed;

@Repository
@RequiredArgsConstructor
public class ClosingFixedCustomRepositoryImpl implements ClosingFixedCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<ClosingFixed> findClosingFixedByDateTimeBetweenStartAndEnd(int cfWeekNum, WeekDay cfWeekDay) {

        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(closingFixed)
                .where(closingFixed.cfUseStatus.eq(UseStatus.USING)
                        .and(closingFixed.cfWeekNum.in(-1, cfWeekNum))
                        .and(closingFixed.cfWeekDay.in(WeekDay.ALL_DAY, cfWeekDay))
                        .and(closingFixed.cfStartTime.loe(LocalTime.now()))
                        .and(closingFixed.cfEndTime.goe(LocalTime.now()))
                )
                .fetchOne());
    }
}
