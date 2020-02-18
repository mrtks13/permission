package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee  e  where e.id=:employeeId ")
    List<Employee> findManagerByEmployeeId(@Param("employeeId") Long employeeId);
}
