package com.yapikredi.permission.service;

import java.time.LocalDate;

/**
 *
 */
public interface HolidayCalculatorService {

    int totalBusinessDaysBetween(LocalDate startDate, LocalDate endDate);


}
