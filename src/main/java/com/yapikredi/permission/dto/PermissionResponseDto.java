package com.yapikredi.permission.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionResponseDto implements Serializable {
    private Long permissionRequestId;
    private Integer numberOfRequestPermissionDay;
    private String status;





}
