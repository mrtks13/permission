package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PermissionRequestRepositoryCustom  {



    List<PermissionRequest> findPermissionRequestListByManager(Long employeeId, WorkFlowStatus status);
}
