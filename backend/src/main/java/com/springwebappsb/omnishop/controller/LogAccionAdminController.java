package com.springwebappsb.omnishop.controller;

import com.springwebappsb.omnishop.dto.response.LogAccionAdminResponseDto;
import com.springwebappsb.omnishop.mapper.internal.LogAccionAdminMapper;
import com.springwebappsb.omnishop.service.LogAccionAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/logs-admin")
@RequiredArgsConstructor
public class LogAccionAdminController {
    private final LogAccionAdminService service;
    private final LogAccionAdminMapper mapper;

    @GetMapping
    public ResponseEntity<List<LogAccionAdminResponseDto>> listar() {
        return ResponseEntity.ok(service.listar().stream().map(mapper::toResponse).toList());
    }
}
