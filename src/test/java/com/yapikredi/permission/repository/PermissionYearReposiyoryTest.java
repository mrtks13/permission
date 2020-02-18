package com.yapikredi.permission.repository;

import com.yapikredi.permission.domain.entity.PermissionYear;
import com.yapikredi.permission.domain.entity.PublicHoliday;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories(basePackageClasses = PermissionYear.class)
@EntityScan(basePackageClasses = PermissionYear.class)
public class PermissionYearReposiyoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}