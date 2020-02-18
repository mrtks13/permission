package com.yapikredi.permission.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    private static final long serialVersionUID = 2486585404573640289L;

    private String name;
    private String lastName;
    private LocalDate startHiringDate;
    private Department department;
    public Employee() {
    }


    public Employee(String name, String lastName, LocalDate startHiringDate) {
        this.name = name;
        this.lastName = lastName;
        this.startHiringDate = startHiringDate;
    }

    @Column(length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getStartHiringDate() {
        return startHiringDate;
    }

    public void setStartHiringDate(LocalDate startHiringDate) {
        this.startHiringDate = startHiringDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departman_id", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }




}
