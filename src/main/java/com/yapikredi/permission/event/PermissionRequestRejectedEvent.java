package com.yapikredi.permission.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestRejectedEvent implements Serializable {
    private static final long serialVersionUID = 6727929810415713312L;

    private Long permissionRequestId;
    private Long approveUserId;


}
