package com.yapikredi.permission.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name="public_holiday")
public class PublicHoliday extends BaseDefEntity {
    private static final long serialVersionUID = 5561227155524207766L;

    private String name;
    private LocalDate date;

    public PublicHoliday() {

    }

    public PublicHoliday(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
