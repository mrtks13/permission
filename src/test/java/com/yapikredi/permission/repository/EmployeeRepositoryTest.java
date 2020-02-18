package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.Department;
import com.yapikredi.permission.domain.entity.Employee;
import com.yapikredi.permission.domain.entity.PermissionYear;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = EmployeeRepository.class)
@EntityScan(basePackageClasses = EmployeeRepository.class)

public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        Employee employee = new Employee("murat", "akkus", LocalDate.of(2018, 1, 15));


        employeeRepository.save(employee);


    }



    @Test
    public void findManagerByEmployeeId() {

        //Given
        Long employeeId = 1L;

        //when
        List<Employee> employeeList = employeeRepository.findManagerByEmployeeId(employeeId);

        //then
        assertEquals(1, employeeList.size());
    }

    @After
    public void tearDown() throws Exception {
        //employeeRepository.deleteAllInBatch();
    }
}