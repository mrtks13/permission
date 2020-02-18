package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PermissionYear;
import com.yapikredi.permission.repository.PermissionYearReposiyory;
import com.yapikredi.permission.util.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class PermissionYearServiceImpl implements PermissionYearService {


    @Value("${permission.advanceDayUseFirstYear}")
    private int advanceDayUseFirstYear = 5;

    private final PermissionYearReposiyory permissionYearReposiyory;

    private static HashMap<Integer, Integer> permissionYearHashMap;


    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public int getNumberOfDayByBetweenDate(LocalDate startDate, LocalDate endDate) {

        int experinceNumberOfYear = DateUtils.getCountOfYear(startDate, endDate);
        if (experinceNumberOfYear == 0) {
            return advanceDayUseFirstYear;
        } else
            return calculateNumberOfDayByYear(experinceNumberOfYear);

    }


    private Integer calculateNumberOfDayByYear(final Integer year) {
        if (permissionYearHashMap == null) {
            log.debug("refreshPermissionYerMap");
            refreshPermissionYerMap();
        }
        return permissionYearHashMap.get(year);
    }


    private void refreshPermissionYerMap() {

        permissionYearHashMap = new HashMap<>();
        List<PermissionYear> permissionYearList = permissionYearReposiyory.findAll();
        log.debug("permissionYearList size:{}", permissionYearList.size());
        PermissionYear maxPermissionYear = permissionYearList
                .stream()
                .max(Comparator.comparing(PermissionYear::getEndYear))
                .orElseThrow(NoSuchElementException::new);
        int numberOfday = 0;
        for (int year = 1; year <= maxPermissionYear.getEndYear(); year++) {
            for (PermissionYear permissionYear : permissionYearList) {
                numberOfday += getNumberOfDay(permissionYear, year);
            }
            if (numberOfday > 0) {
                permissionYearHashMap.put(year, numberOfday);
            }
            log.debug("numberOfday:{},year:{}", numberOfday, year);
        }
    }


    private int getNumberOfDay(PermissionYear permissionYear, int value) {
        return (value >= permissionYear.getStartYear() && value <= permissionYear.getEndYear()) ? permissionYear.getNumberOfDay() : 0;
    }


}
