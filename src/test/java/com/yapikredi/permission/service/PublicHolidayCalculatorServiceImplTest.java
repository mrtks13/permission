package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PublicHoliday;
import com.yapikredi.permission.repository.HolidayRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PublicHolidayCalculatorServiceImplTest {


    @InjectMocks
    private HolidayCalculateServiceImpl holidayCalculateService;

    @Spy
    List<PublicHoliday> publicHolidayList;

    @Mock
    private HolidayRepository holidayRepository;


    @Before
    public void setUp() throws Exception {

        publicHolidayList = new ArrayList<>();
        //23 NisaN tatili
        publicHolidayList.add(new PublicHoliday("23 Nisan", LocalDate.of(2020, 4, 23)));
        publicHolidayList.add(new PublicHoliday("1 Mayıs", LocalDate.of(2020, 5, 1)));

    }


    @Test
    public void whenTwoDate_ThenOK() {

        //given
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 4, 24);

        Mockito.when(holidayRepository.findHolidaysByDateBetween(startDate, endDate)).thenReturn(publicHolidayList);

        //when
        int result = holidayCalculateService.totalBusinessDaysBetween(startDate, endDate);
        //then


        Assert.assertEquals(3, result);

    }


    @Test
    public void givenWeekend_thenfindHolidaysByDateBetween_ThenOK() {

        //given
        LocalDate startDate = LocalDate.of(2020, 4, 17);
        LocalDate endDate = LocalDate.of(2020, 4, 24);

        Mockito.when(holidayRepository.findHolidaysByDateBetween(startDate, endDate)).thenReturn(publicHolidayList);

        //when
        int result = holidayCalculateService.totalBusinessDaysBetween(startDate, endDate);
        //then

        System.out.println();
        Assert.assertEquals(4, result);

    }






}