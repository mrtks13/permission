package com.yapikredi.permission.service;

import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.event.PermissionRequestApprovedEvent;
import com.yapikredi.permission.event.PermissionRequestRejectedEvent;

public interface PermissionHistoryService {


    Integer getNumberOfUsedPermission(Long employeeId);


}
