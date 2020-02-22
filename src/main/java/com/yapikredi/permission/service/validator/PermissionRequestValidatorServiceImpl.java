package com.yapikredi.permission.service.validator;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.exception.PermissionDayNotEnoughException;
import com.yapikredi.permission.exception.ValidationInputException;
import com.yapikredi.permission.service.PermissionHistoryService;
import com.yapikredi.permission.service.PermissionYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class PermissionRequestValidatorServiceImpl implements PermissionRequestValidatorService {

    private final PermissionHistoryService permissionHistoryService;

    private final PermissionYearService permissionYearService;

    private Predicate<PermissionRequestDto> isFutureDate = permissionRequest ->
            (permissionRequest.getHolidayStartDate().isBefore(permissionRequest.getWorkingStartDate()));


    @Override
    public void validatePermissionRequestInput(PermissionRequestDto permissionRequestDto) {
        if (!isFutureDate.test(permissionRequestDto)) {
            throw new ValidationInputException("asasas");
        }
    }


    @Override
    public void validatePermissionRequest(PermissionRequest permissionRequest) {

        int numberOfDeservePermissionDay = permissionYearService.getNumberOfDayByBetweenDate(permissionRequest.getEmployee().getStartHiringDate(), permissionRequest.getHolidayStartDate());

        int numberOfUsedPermissionDay = permissionHistoryService.getNumberOfUsedPermission(permissionRequest.getEmployee().getId());

        Boolean isNumberOfCanUsePermissionDay = getNumberOfCanUsePermissionDay(numberOfDeservePermissionDay, numberOfUsedPermissionDay, permissionRequest.getNumberOfRequestPermissionDay());

        if (!isNumberOfCanUsePermissionDay) {
            throw new PermissionDayNotEnoughException("error.notenaughexcepition");
        }
    }


    private Boolean getNumberOfCanUsePermissionDay(int numberOfDeservePermissionDay, int numberOfUsedPermissionDay, int numberOfRequestPermisionDay) {

        return (numberOfDeservePermissionDay - numberOfUsedPermissionDay + numberOfRequestPermisionDay) > 0;
    }


}
