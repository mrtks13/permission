package com.yapikredi.permission.controller;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.event.PermissionRequestApprovedEvent;
import com.yapikredi.permission.event.PermissionRequestRejectedEvent;
import com.yapikredi.permission.exception.PermissionAppException;
import com.yapikredi.permission.exception.ValidationInputException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class PermissionRequestControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    @Transactional
    public void givenWeekendDays_whencreatePermissionRequest_thenOK() throws Exception {
        //given
        Long employeeId = 1L;
        String description = "test request";

        String permissionStartDateStr = "2020-02-17";
        String permissionEndDateStr = "2020-02-28";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate permissionStartDate = LocalDate.parse(permissionStartDateStr, formatter);
        LocalDate permissionEndDate = LocalDate.parse(permissionEndDateStr, formatter);

        PermissionRequestDto permissionRequestDto = new PermissionRequestDto();
        permissionRequestDto.setEmployeeId(employeeId);
        permissionRequestDto.setHolidayStartDate(permissionStartDate);
        permissionRequestDto.setWorkingStartDate(permissionEndDate);
        permissionRequestDto.setDescription(description);
        String content = super.mapToJson(permissionRequestDto);
        System.out.println( content);

        String uri = "/permissionrequest/create";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8;");
        httpHeaders.add("Accept-Language", "tr");

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andReturn();
        System.out.println( mvcResult.getRequest().getRequestURI());
        int status = mvcResult.getResponse().getStatus();
        //then
        assertEquals(HttpStatus.CREATED.value(), status);

        PermissionResponseDto permissionResponseDto = super.mapFromJson(mvcResult.getResponse().getContentAsString(), PermissionResponseDto.class);

        System.out.println( mvcResult.getRequest().getRequestURI());

        assertEquals(9, permissionResponseDto.getNumberOfRequestPermissionDay().intValue());
    }


    @Test
    @Transactional
    public void givenWeekendDays_whenrejectedPermissionRequest_thenOK() throws Exception {
        //given
        Long employeeId = 1L;
        Long permissionRequestId = 2L;


        PermissionRequestRejectedEvent permissionRequestRejectedEvent = new PermissionRequestRejectedEvent(permissionRequestId,employeeId);
        String content = super.mapToJson(permissionRequestRejectedEvent);
        System.out.println( content);

        String uri = "/permissionrequest/reject";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8;");
        httpHeaders.add("Accept-Language", "tr");

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andReturn();
        System.out.println( mvcResult.getRequest().getRequestURI());
        int status = mvcResult.getResponse().getStatus();
        //then
        assertEquals(HttpStatus.NO_CONTENT.value(), status);

    }


    @Test
    @Transactional
    public void givenWeekendDays_whenapprovedPermissionRequest_thenOK() throws Exception {
        //given
        Long employeeId = 1L;
        Long permissionRequestId = 2L;


        PermissionRequestApprovedEvent permissionRequestApprovedEvent = new PermissionRequestApprovedEvent(permissionRequestId,employeeId);
        String content = super.mapToJson(permissionRequestApprovedEvent);
        System.out.println( content);

        String uri = "/permissionrequest/approve";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8;");
        httpHeaders.add("Accept-Language", "tr");

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andReturn();
        System.out.println( mvcResult.getRequest().getRequestURI());
        int status = mvcResult.getResponse().getStatus();
        //then
        assertEquals(HttpStatus.NO_CONTENT.value(), status);

    }




    @Test(expected = ValidationInputException.class)
    public void givenHolidaysDays_whencreatePermissionRequest_thenOK() throws Exception {
        //given
        Long employeeId = 1L;
        String description = "test request";

        String permissionStartDateStr = "2020-04-21";
        String permissionEndDateStr = "2020-04-20";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate permissionStartDate = LocalDate.parse(permissionStartDateStr, formatter);
        LocalDate permissionEndDate = LocalDate.parse(permissionEndDateStr, formatter);

        PermissionRequestDto permissionRequestDto = new PermissionRequestDto();
        permissionRequestDto.setEmployeeId(employeeId);
        permissionRequestDto.setHolidayStartDate(permissionStartDate);
        permissionRequestDto.setWorkingStartDate(permissionEndDate);
        permissionRequestDto.setDescription(description);
        String content = super.mapToJson(permissionRequestDto);
        System.out.println( content);

        String uri = "/permissionrequest/create/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8;");
        httpHeaders.add("Accept-Language", "tr");

        //when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andReturn();
        System.out.println( mvcResult.getRequest().getRequestURI());
        int status = mvcResult.getResponse().getStatus();
        //then
        assertEquals(HttpStatus.CREATED.value(), status);

    }
}