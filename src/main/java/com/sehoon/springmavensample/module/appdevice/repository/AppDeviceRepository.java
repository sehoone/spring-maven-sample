package com.sehoon.springmavensample.module.appdevice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sehoon.springmavensample.module.appdevice.domain.AppDevice;

/**
 * Spring Data SQL repository for the AppDevice entity
 */
public interface AppDeviceRepository extends JpaRepository<AppDevice, Long> {
    Optional<AppDevice> findOneByUuid(String uuid);

    Optional<AppDevice> findFirstByUserIdOrderByCreatedBy(Long userId);

}
