package com.yapikredi.permission.service.validator;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;

public interface PermissionRequestValidatorService {

    void validatePermissionRequestInput(PermissionRequestDto permissionRequestDto);

    void validatePermissionRequest(PermissionRequest permissionRequest);
}
