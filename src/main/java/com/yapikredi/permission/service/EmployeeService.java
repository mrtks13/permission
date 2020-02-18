package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;

public interface EmployeeService {

    Employee findById(Long employeeId);

}
