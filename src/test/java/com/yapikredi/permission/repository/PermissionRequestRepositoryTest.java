package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.PublicHoliday;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = PermissionRequestRepository.class)
@EntityScan(basePackageClasses = PermissionRequest.class)
public class PermissionRequestRepositoryTest {

    @Autowired
    private PermissionRequestRepository permissionRequestRepository;


    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void giwenEmployeeID_whenfindPermissionRequestByEmployee_ThenShouldOneRecord() {

        //given
        Long employeeId = 1L;


        //when
        List<PermissionRequest> permissionRequestList = permissionRequestRepository.findPermissionRequestByEmployee(employeeId, WorkFlowStatus.APPROVED);

        //then

        Assert.assertEquals(1, permissionRequestList.size());

    }


    @Test

    public void giwenEmployeeID_whenfindPermissionRequestListByManager_ThenShouldOneRecord() {

        //given
        Long employeeId = 1L;


        //when
        List<PermissionRequest> permissionRequestList = permissionRequestRepository.findPermissionRequestListByManager(employeeId, WorkFlowStatus.REQUESTED);

        //then

        Assert.assertEquals(1, permissionRequestList.size());

    }

    @After
    public void tearDown() throws Exception {
    }


}