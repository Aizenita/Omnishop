package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.LogAccionAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAccionAdminRepository extends JpaRepository<LogAccionAdmin, Long> {}

