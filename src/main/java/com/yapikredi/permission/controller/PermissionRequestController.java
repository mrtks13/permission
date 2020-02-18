package com.yapikredi.permission.controller;


import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.domain.entity.WorkFlowStatus;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import com.yapikredi.permission.event.PermissionRequestApprovedEvent;
import com.yapikredi.permission.event.PermissionRequestRejectedEvent;
import com.yapikredi.permission.service.PermissionRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "permissionrequest/")
public class PermissionRequestController {
    private final PermissionRequestService permissionRequestService;

    public PermissionRequestController(PermissionRequestService permissionRequestService) {
        this.permissionRequestService = permissionRequestService;
    }


    @PostMapping(value = "create",consumes =MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PermissionResponseDto createPermissionRequest( @NotNull @Valid  @RequestBody final PermissionRequestDto PermissionRequestDto) {
        return permissionRequestService.createPermissionRequest(PermissionRequestDto);
    }

    @PutMapping(value = "reject", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectedPermissionRequest(@NotNull @Valid @RequestBody final PermissionRequestRejectedEvent permissionRequestRejectedEvent) {
        permissionRequestService.rejectedPermissionRequest(permissionRequestRejectedEvent);
    }

    @PutMapping(value = "approve", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void approvedPermissionRequest(@NotNull @Valid @RequestBody final PermissionRequestApprovedEvent permissionRequestApprovedEvent) {
        permissionRequestService.approvedPermissionRequest(permissionRequestApprovedEvent);
    }

    @GetMapping(value = "status/{manager}/{status}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<PermissionResponseDto> findPermissionRequestList(@PathVariable("manager") Long managerId, @PathVariable("status") WorkFlowStatus status) {
        return permissionRequestService.findPermissionRequestList(managerId, status);
    }


}
