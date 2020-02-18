package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.event.PermissionRequestApprovedEvent;
import com.yapikredi.permission.event.PermissionRequestRejectedEvent;
import com.yapikredi.permission.exception.EmployeeNotFoundException;
import com.yapikredi.permission.repository.PermissionRequestRepository;
import com.yapikredi.permission.service.mapper.PermissionRequestMapper;
import com.yapikredi.permission.service.validator.PermissionRequestValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class PermissionRequestServiceImpl implements PermissionRequestService {


    private final PermissionRequestRepository permissionRequestRepository;
    private final PermissionRequestValidatorService permissionRequestValidatorService;
    private final EmployeeService employeeService;
    private final HolidayCalculatorService holidayCalculateService;

    /*
     * createPermissionRequest
     * */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PermissionResponseDto createPermissionRequest(PermissionRequestDto permissionRequestDto) {
        log.debug("create permission info:{}", permissionRequestDto.toString());
        //validate request input
        permissionRequestValidatorService.validatePermissionRequestInput(permissionRequestDto);
        Employee employee = employeeService.findById(permissionRequestDto.getEmployeeId());

        PermissionRequest permissionRequest = PermissionRequestMapper.INSTANCE.toEntity(permissionRequestDto);

        int NumberOfRequestPermissionDay = holidayCalculateService.totalBusinessDaysBetween(permissionRequest.getHolidayStartDate(), permissionRequest.getWorkingStartDate());
        permissionRequest.setNumberOfRequestPermissionDay(NumberOfRequestPermissionDay);
        permissionRequest.setEmployee(employee);


        //validate employee Request
        permissionRequestValidatorService.validatePermissionRequest(permissionRequest);

        permissionRequest.setStatus(WorkFlowStatus.REQUESTED);
        permissionRequestRepository.save(permissionRequest);

        return PermissionRequestMapper.INSTANCE.toDto(permissionRequest);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void approvedPermissionRequest(PermissionRequestApprovedEvent permissionRequestApprovedEvent) {

        PermissionRequest permissionRequest = permissionRequestRepository.
                findById(permissionRequestApprovedEvent.getPermissionRequestId()).orElseThrow(EmployeeNotFoundException::new);

        permissionRequest.setStatus(WorkFlowStatus.APPROVED);
        permissionRequestRepository.save(permissionRequest);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void rejectedPermissionRequest(PermissionRequestRejectedEvent permissionRequestRejectedEvent) {

        PermissionRequest permissionRequest = permissionRequestRepository.
                findById(permissionRequestRejectedEvent.getPermissionRequestId()).orElseThrow(EmployeeNotFoundException::new);

        permissionRequest.setStatus(WorkFlowStatus.REJECTED);
        permissionRequestRepository.save(permissionRequest);

    }

    @Override
    public List<PermissionResponseDto> findPermissionRequestList(Long managerId, WorkFlowStatus  workFlowStatus) {
        List<PermissionRequest> permissionRequestList = permissionRequestRepository.findPermissionRequestListByManager(managerId,workFlowStatus);

        return permissionRequestList.stream()
                .filter(Objects::nonNull)
                .map(permissionRequest -> PermissionRequestMapper.INSTANCE.toDto(permissionRequest)).collect(Collectors.toList());


    }
}
