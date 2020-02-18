package com.yapikredi.permission;

import com.yapikredi.permission.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.yapikredi.permission.repository"})
@EntityScan(basePackages = "com.yapikredi.permission.domain.entity")
@EnableConfigurationProperties({ApplicationConfiguration.class})
public class PermissionManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionManagementApplication.class, args);
    }

}
