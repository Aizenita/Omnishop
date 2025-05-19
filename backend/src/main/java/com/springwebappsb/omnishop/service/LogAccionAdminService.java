package com.springwebappsb.omnishop.service;

import com.springwebappsb.omnishop.entity.LogAccionAdmin;
import com.springwebappsb.omnishop.repository.LogAccionAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogAccionAdminService {
    private final LogAccionAdminRepository repository;

    public List<LogAccionAdmin> listar() {
        return repository.findAll();
    }
}
