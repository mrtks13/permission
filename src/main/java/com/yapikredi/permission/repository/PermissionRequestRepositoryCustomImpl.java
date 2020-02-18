package com.yapikredi.permission.repository;


import com.yapikredi.permission.domain.entity.Department;
import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionRequestRepositoryCustomImpl implements PermissionRequestRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PermissionRequest> findPermissionRequestListByManager(Long employeeId, WorkFlowStatus status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PermissionRequest> cq = cb.createQuery(PermissionRequest.class);

        Root<PermissionRequest> permissionRequestRoot = cq.from(PermissionRequest.class);
        Join<PermissionRequest, Employee> employee = permissionRequestRoot.join("employee", JoinType.INNER);
        Join<Employee, Department> departmentJoin = employee.join("department", JoinType.INNER);
        Join<Department, Employee> manager = departmentJoin.join("manager", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();


        if (status != null) {
            predicates.add(cb.equal(permissionRequestRoot.get("status"), status));
        }
        predicates.add(cb.equal(manager.get("id"), employeeId));

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
