package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRequestRepository extends JpaRepository<PermissionRequest, Long>, PermissionRequestRepositoryCustom {

    @Query("select p from PermissionRequest  p where p.employee.id=?1 and p.status=?2 ")
    List<PermissionRequest> findPermissionRequestByEmployee(Long employeeId, WorkFlowStatus status);
}
