package com.sideProject.qrOrder.entity;

import com.sideProject.qrOrder.entity.Common.BaseEntity;
import com.sideProject.qrOrder.entity.Common.ENUM.UseStatus;
import com.sideProject.qrOrder.entity.Common.ENUM.WeekDay;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "TB_CLOSING_FIXED")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClosingFixed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CF_ID")
    private Long cfId;

    @Column(name = "CF_WEEK_NUM")
    private int cfWeekNum;
    // -1 이면 전체

    @Column(name = "CF_WEEK_DAY")
    private WeekDay cfWeekDay;

    @Column(name = "CF_START_TIME", nullable = false)
    private LocalTime cfStartTime;

    @Column(name = "CF_END_TIME", nullable = false)
    private LocalTime cfEndTime;

    @Column(name = "CF_USE_STATUS", nullable = false)
    private UseStatus cfUseStatus;

    @Builder
    public ClosingFixed(int cfWeekNum,
                        WeekDay cfWeekDay,
                        LocalTime cfStartTime,
                        LocalTime cfEndTime,
                        UseStatus cfUseStatus) {
        this.cfWeekNum = cfWeekNum;
        this.cfWeekDay = cfWeekDay;
        this.cfStartTime = cfStartTime;
        this.cfEndTime = cfEndTime;
        this.cfUseStatus = cfUseStatus;
    }

    public void toggleUseStatus() {
        if (this.cfUseStatus == UseStatus.USING) {
            this.cfUseStatus = UseStatus.NOT_USING;
        } else {
            this.cfUseStatus = UseStatus.USING;
        }
    }
}
