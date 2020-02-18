package com.yapikredi.permission.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name="permission_year")
public class PermissionYear extends BaseDefEntity  implements Comparable<Integer>{

    private Integer startYear;
    private Integer endYear;
    private Integer numberOfDay;

    public PermissionYear() {

    }

    public PermissionYear(Integer startYear, Integer endYear, Integer numberOfDay) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.numberOfDay = numberOfDay;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(Integer numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    @Override
    public int compareTo(Integer year) {

        return (year >= this.getStartYear() && year <= this.endYear) ? this.getNumberOfDay() : 0;
    }
}
