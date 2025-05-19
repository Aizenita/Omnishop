package com.springwebappsb.omnishop.mapper.internal;

import com.springwebappsb.omnishop.dto.response.LogAccionAdminResponseDto;
import com.springwebappsb.omnishop.entity.LogAccionAdmin;
import org.springframework.stereotype.Component;

@Component
public class LogAccionAdminMapper {
    public LogAccionAdminResponseDto toResponse(LogAccionAdmin log) {
        return LogAccionAdminResponseDto.builder()
                .id(log.getId())
                .adminId(log.getAdmin() != null ? log.getAdmin().getId() : null)
                .accion(log.getAccion())
                .fecha(log.getFecha())
                .build();
    }
}
