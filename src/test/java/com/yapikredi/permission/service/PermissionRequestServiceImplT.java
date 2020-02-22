package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/data/import.sql")
class PermissionRequestServiceImplT {

    @Autowired
    private PermissionRequestService permissionRequestService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPermissionRequest() {

        Long employeeId = 1L;

        String permissionStartDateStr = "2020-01-12";
        String permissionEndDateStr = "2020-01-15";


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate permissionStartDate = LocalDate.parse(permissionStartDateStr, formatter);
        LocalDate permissionEndDate = LocalDate.parse(permissionEndDateStr, formatter);

        PermissionRequestDto permissionRequestDto = new PermissionRequestDto();
        permissionRequestDto.setEmployeeId(employeeId);
        permissionRequestDto.setHolidayStartDate(permissionStartDate);
        permissionRequestDto.setWorkingStartDate(permissionEndDate);

        //when
        PermissionResponseDto permissionResponseDto= permissionRequestService.createPermissionRequest(permissionRequestDto);

        //then
        Assert.assertEquals(2,permissionResponseDto.getNumberOfRequestPermissionDay().intValue());


    }
}