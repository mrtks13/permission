package com.yapikredi.permission.service;

import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.exception.EmployeeNotFoundException;
import com.yapikredi.permission.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public Employee findById(Long employeeId) {

        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }




}
