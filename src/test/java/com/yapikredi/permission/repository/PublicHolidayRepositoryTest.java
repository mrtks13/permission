package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PublicHoliday;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = HolidayRepository.class)
@EntityScan(basePackageClasses = PublicHoliday.class)
public class PublicHolidayRepositoryTest {

    @Autowired
    private HolidayRepository holidayRepository;

    @Before
    public void setUp() throws Exception {

        ArrayList<PublicHoliday> publicHolidayList = new ArrayList<>();

        PublicHoliday holiday = new PublicHoliday();
        holiday.setDate(LocalDate.of(2020, 4, 23));
        holiday.setName("23 Nisan Tatili");
        publicHolidayList.add(holiday);

        holiday = new PublicHoliday();
        holiday.setDate(LocalDate.of(2020, 5, 1));
        holiday.setName("23 Nisan Tatili");
        publicHolidayList.add(holiday);
        holidayRepository.saveAll(publicHolidayList);


    }

    @Test
    public void whenTwoDate_ThenOK() {

        //given
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 4, 23);


        //when
        List<PublicHoliday> daysOfHolidayList = holidayRepository.findHolidaysByDateBetween(startDate, endDate);

        //then

        Assert.assertEquals(1, daysOfHolidayList.size());

    }

    @Test
    public void whentotalBusinessDaysBetween_ThenOK() {

        //given
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 4, 23);


        //when
        List<PublicHoliday> daysOfHolidayList = holidayRepository.findHolidaysByDateBetween(startDate, endDate);

        //then

        Assert.assertEquals(1, daysOfHolidayList.size());

    }



    @After
    public void tearDown() throws Exception {

        holidayRepository.deleteAllInBatch();
    }
}