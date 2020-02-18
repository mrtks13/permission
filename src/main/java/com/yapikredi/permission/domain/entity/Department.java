package com.yapikredi.permission.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name="department")
public class Department extends BaseEntity {

    private static final long serialVersionUID = 1633188960686737238L;

    private String name;
    private List<Employee> employeeList;
    private Employee manager;

    @Column(length = 100)
    public String getName() {
        return name;
    }





    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @ManyToOne
    @JoinColumn(name = "manager_employee_id")
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }


}
