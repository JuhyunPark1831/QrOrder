package com.sideProject.qrOrder.common.util;

import com.sideProject.qrOrder.entity.Common.ENUM.WeekDay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DateUtil {

    public WeekDay getWeekDay(LocalDate localDate) {
        return WeekDay.valueOf(localDate.getDayOfWeek().name());
    }

    public int getWeekDayNum(LocalDate localDate) {

        DayOfWeek targetDay = localDate.getDayOfWeek();
        int count = 0;

        for (int day = 1; day <= localDate.getDayOfMonth(); day++) {
            LocalDate d = localDate.withDayOfMonth(day);
            if (d.getDayOfWeek() == targetDay) {
                count++;
            }
        }

        return count;
    }
}
