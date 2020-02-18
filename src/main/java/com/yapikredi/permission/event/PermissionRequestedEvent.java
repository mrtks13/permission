package com.yapikredi.permission.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestedEvent implements Serializable {
    private static final long serialVersionUID = -8490374795940776263L;

    private Long permissionRequestId;
}
