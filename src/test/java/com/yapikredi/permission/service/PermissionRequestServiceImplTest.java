package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.repository.PermissionRequestRepository;
import com.yapikredi.permission.service.validator.PermissionRequestValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PermissionRequestServiceImplTest {

    @InjectMocks
    private PermissionRequestServiceImpl permissionRequestService;

    @Mock
    private PermissionRequestValidatorService permissionRequestValidatorService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private HolidayCalculatorService holidayCalculateService;

    @Mock
    private PermissionRequestRepository permissionRequestRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void when_createPermissionRequest_OK() {

        //Given

        Long employeeId = 1L;

        Employee employee = new Employee();
        employee.setId(employeeId);

        String permissionStartDateStr = "2020-01-12";
        String permissionEndDateStr = "2020-01-15";


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate permissionStartDate = LocalDate.parse(permissionStartDateStr, formatter);
        LocalDate permissionEndDate = LocalDate.parse(permissionEndDateStr, formatter);

        PermissionRequestDto permissionRequestDto = new PermissionRequestDto();
        permissionRequestDto.setEmployeeId(employeeId);
        permissionRequestDto.setHolidayStartDate(permissionStartDate);
        permissionRequestDto.setWorkingStartDate(permissionEndDate);

        doNothing().when(permissionRequestValidatorService).validatePermissionRequestInput(any(PermissionRequestDto.class));
        when(employeeService.findById(employeeId)).thenReturn(employee);
        when(employeeService.findById(employeeId)).thenReturn(employee);
        doNothing().when(permissionRequestValidatorService).validatePermissionRequest(any(PermissionRequest.class));
        when(holidayCalculateService.totalBusinessDaysBetween(permissionStartDate,permissionEndDate)).thenReturn(2);
        //when

        PermissionResponseDto permissionResponseDto = permissionRequestService.createPermissionRequest(permissionRequestDto);

        //then
        assertEquals(2,permissionResponseDto.getNumberOfRequestPermissionDay());

    }


    @Test
    void saveAction() {
    }
}