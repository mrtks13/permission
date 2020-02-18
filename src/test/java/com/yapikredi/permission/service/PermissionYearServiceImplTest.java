package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PermissionYear;
import com.yapikredi.permission.repository.PermissionYearReposiyory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PermissionYearServiceImplTest {


    @InjectMocks
    private PermissionYearServiceImpl permissionYearService;

    private  List<PermissionYear> permissionYears = new ArrayList<>();

    @Mock
    private PermissionYearReposiyory permissionYearReposiyory;


    @Before
    public void setUp() throws Exception {
        PermissionYear permissionYear1 = new PermissionYear(1, 5, 15);
        PermissionYear permissionYear2 = new PermissionYear(6, 10, 18);
        PermissionYear permissionYear3 = new PermissionYear(11, 99, 24);
        permissionYears.add(permissionYear1);
        permissionYears.add(permissionYear2);
        permissionYears.add(permissionYear3);

        Mockito.when(permissionYearReposiyory.findAll()).thenReturn(permissionYears);
    }

    @Test
    public void whenZeroYearRange_getNumberOfDayByYear_thenShould() {

        //given
        String startDateStr = "2020-01-12";
        String endDateStr = "2020-02-16";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);


        //when
        Integer a=  permissionYearService.getNumberOfDayByBetweenDate(startDate, endDate);

        //Then
        assertEquals(5,a);

    }


    @Test
    public void whenOneYearRange_getNumberOfDayByYear_thenShould() {

        //given
        String startDateStr = "2019-01-12";
        String endDateStr = "2020-02-16";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);



        //when
        Integer a=  permissionYearService.getNumberOfDayByBetweenDate(startDate, endDate);

        //Then
       assertEquals(15,a);

    }


    @Test
    public void whenSevenYearRange_getNumberOfDayByYear_thenShould() {

        //given
        String startDateStr = "2012-01-12";
        String endDateStr = "2020-02-16";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        //when
        Integer a=  permissionYearService.getNumberOfDayByBetweenDate(startDate, endDate);

        //Then
        assertEquals(129,a);

    }

    @Test
    public void whenAfter10YearRange_getNumberOfDayByYear_thenShould() {

        //given
        String startDateStr = "2009-01-12";
        String endDateStr = "2020-02-16";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);


        //when
        Integer a=  permissionYearService.getNumberOfDayByBetweenDate(startDate, endDate);

        //Then
        assertEquals(189,a);

    }

}