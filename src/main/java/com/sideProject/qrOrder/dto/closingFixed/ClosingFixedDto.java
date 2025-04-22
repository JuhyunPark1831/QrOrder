package com.sideProject.qrOrder.dto.closingFixed;

import com.sideProject.qrOrder.entity.ClosingFixed;
import com.sideProject.qrOrder.entity.Common.ENUM.UseStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.WeekDay;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosingFixedDto {

    private Long cfId;
    private int cfWeekNum;
    private WeekDay cfWeekDay;
    private LocalTime cfStartTime;
    private LocalTime cfEndTime;
    private UseStatus cfUseStatus;

    @Builder
    public ClosingFixedDto(Long cfId,
                           int cfWeekNum,
                           WeekDay cfWeekDay,
                           LocalTime cfStartTime,
                           LocalTime cfEndTime,
                           UseStatus cfUseStatus) {
        this.cfId = cfId;
        this.cfWeekNum = cfWeekNum;
        this.cfWeekDay = cfWeekDay;
        this.cfStartTime = cfStartTime;
        this.cfEndTime = cfEndTime;
        this.cfUseStatus = cfUseStatus;
    }

    public static ClosingFixedDto from(ClosingFixed closingFixed) {
        return ClosingFixedDto.builder()
                .cfId(closingFixed.getCfId())
                .cfWeekNum(closingFixed.getCfWeekNum())
                .cfWeekDay(closingFixed.getCfWeekDay())
                .cfStartTime(closingFixed.getCfStartTime())
                .cfEndTime(closingFixed.getCfEndTime())
                .cfUseStatus(closingFixed.getCfUseStatus())
                .build();
    }
}
