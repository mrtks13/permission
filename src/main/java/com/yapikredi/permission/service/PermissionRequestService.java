package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.event.PermissionRequestApprovedEvent;
import com.yapikredi.permission.event.PermissionRequestRejectedEvent;

import java.util.List;

public interface PermissionRequestService {

    PermissionResponseDto createPermissionRequest(PermissionRequestDto permissionRequestDto);

    void rejectedPermissionRequest(PermissionRequestRejectedEvent permissionRequestRejectedEvent);

    void approvedPermissionRequest(PermissionRequestApprovedEvent permissionRequestApprovedEvent);


    List<PermissionResponseDto> findPermissionRequestList(Long managerId, WorkFlowStatus status);
}
