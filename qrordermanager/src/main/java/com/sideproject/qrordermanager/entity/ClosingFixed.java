package com.sideproject.qrordermanager.entity;

import com.sideproject.qrordermanager.entity.Common.BaseEntity;
import com.sideproject.qrordermanager.entity.Common.ENUM.WeekDay;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public int cfWeekNum;

    @Column(name = "CF_WEEK_DAY")
    public WeekDay cfWeekDay;

    @Column(name = "CF_START_TIME", nullable = false)
    private LocalTime cfStartTime;

    @Column(name = "CF_END_TIME", nullable = false)
    private LocalTime cfEndTime;

    @Builder
    public ClosingFixed(int cfWeekNum,
                        WeekDay cfWeekDay,
                        LocalTime cfStartTime,
                        LocalTime cfEndTime) {
        this.cfWeekNum = cfWeekNum;
        this.cfWeekDay = cfWeekDay;
        this.cfStartTime = cfStartTime;
        this.cfEndTime = cfEndTime;
    }
}
