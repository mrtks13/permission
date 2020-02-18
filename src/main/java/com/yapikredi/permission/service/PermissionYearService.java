package com.yapikredi.permission.service;

import java.time.LocalDate;

public interface PermissionYearService {

    int getNumberOfDayByBetweenDate(LocalDate startDate, LocalDate endDate);
}
