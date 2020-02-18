package com.yapikredi.permission.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "permission_request")
public class PermissionRequest extends BaseEntity {
    private static final long serialVersionUID = -6735990292583443539L;

    private Employee employee;
    private WorkFlowStatus status = WorkFlowStatus.REQUESTED;
    private LocalDate holidayStartDate;
    private LocalDate workingStartDate;
    private String description;
    private Integer numberOfRequestPermissionDay;



    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(name = "status", length = 30)
    @Enumerated(value = EnumType.STRING)
    public WorkFlowStatus getStatus() {
        return status;
    }

    public void setStatus(WorkFlowStatus status) {
        this.status = status;
    }


    public LocalDate getHolidayStartDate() {
        return holidayStartDate;
    }

    public void setHolidayStartDate(LocalDate holidayStartDate) {
        this.holidayStartDate = holidayStartDate;
    }

    public LocalDate getWorkingStartDate() {
        return workingStartDate;
    }

    public void setWorkingStartDate(LocalDate workingStartDate) {
        this.workingStartDate = workingStartDate;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "number_of_request_permission_day")
    public Integer getNumberOfRequestPermissionDay() {
        return numberOfRequestPermissionDay;
    }

    public void setNumberOfRequestPermissionDay(Integer numberOfRequestPermissionDay) {
        this.numberOfRequestPermissionDay = numberOfRequestPermissionDay;
    }
}


