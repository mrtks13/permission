package com.yapikredi.permission.service.mapper;

import com.yapikredi.permission.domain.entity.PermissionRequest;
import com.yapikredi.permission.dto.PermissionRequestDto;
import com.yapikredi.permission.dto.PermissionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PermissionRequestMapper {

    PermissionRequestMapper INSTANCE = Mappers.getMapper(PermissionRequestMapper.class);

    PermissionRequest toEntity(PermissionRequestDto dto);

    PermissionResponseDto toDto(PermissionRequest entity);
}
