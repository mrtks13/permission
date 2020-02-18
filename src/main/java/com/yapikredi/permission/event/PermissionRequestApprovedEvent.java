package com.yapikredi.permission.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestApprovedEvent implements Serializable {

    private static final long serialVersionUID = 6374928104102185477L;
    private Long permissionRequestId;
    private Long approveUserId;
}
