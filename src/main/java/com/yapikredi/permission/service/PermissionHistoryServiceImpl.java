package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import com.yapikredi.permission.repository.PermissionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PermissionHistoryServiceImpl implements PermissionHistoryService {

    private final PermissionRequestRepository permissionRequestRepository;


    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Integer getNumberOfUsedPermission(Long employeeId) {
        return permissionRequestRepository.findPermissionRequestByEmployee(employeeId, WorkFlowStatus.APPROVED).stream()
                .filter(Objects::nonNull)
                .mapToInt(PermissionRequest::getNumberOfRequestPermissionDay)
                .filter(Objects::nonNull)
                .sum();


    }

}
