package com.yapikredi.permission.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class PermissionRequestDto implements Serializable {

    private static final long serialVersionUID = 8310781001709274188L;

    private Long employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate holidayStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate workingStartDate;
    private String description;

}

