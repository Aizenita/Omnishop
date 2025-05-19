package com.springwebappsb.omnishop.repository;

import com.springwebappsb.omnishop.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {}

