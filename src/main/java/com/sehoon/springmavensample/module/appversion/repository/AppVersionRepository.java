package com.sehoon.springmavensample.module.appversion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sehoon.springmavensample.module.appversion.domain.AppVersion;

/**
 * Spring Data SQL repository for the AppVersion entity
 */
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    Optional<AppVersion> findFirstByOsCodeOrderByVersionDesc(String osCode);
    
}
