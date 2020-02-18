package com.yapikredi.permission.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class DateUtils {

    public static int getCountOfYear(LocalDate startDate, LocalDate EndDate) {
        return Period.between(startDate, EndDate).getYears();
    }

    public static boolean notWeekend(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

}
