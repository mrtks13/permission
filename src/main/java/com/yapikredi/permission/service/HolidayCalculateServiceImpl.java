package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PublicHoliday;
import com.yapikredi.permission.repository.HolidayRepository;
import com.yapikredi.permission.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class HolidayCalculateServiceImpl implements HolidayCalculatorService {

    private final HolidayRepository holidayRepository;

    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public int totalBusinessDaysBetween(LocalDate start, LocalDate end) {
        final List<LocalDate> holidayList = holidayRepository.findHolidaysByDateBetween(start, end).stream()
                .map(PublicHoliday::getDate).collect(Collectors.toList());
        long numOfDaysBetween = ChronoUnit.DAYS.between(start, end);

        long countDay = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(start::plusDays)
                .filter(localDate -> !holidayList.contains(localDate))
                .filter(DateUtils::notWeekend)
                .count();

        log.debug("calculate totalBusinessDaysBetween:{}", countDay);
        return (int) countDay;
    }




}
