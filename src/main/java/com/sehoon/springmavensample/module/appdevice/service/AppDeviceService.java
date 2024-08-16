package com.sehoon.springmavensample.module.appdevice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sehoon.springmavensample.module.appdevice.domain.AppDevice;
import com.sehoon.springmavensample.module.appdevice.repository.AppDeviceRepository;
import com.sehoon.springmavensample.module.appdevice.service.dto.AppDeviceDTO;
import com.sehoon.springmavensample.module.appdevice.service.mapper.AppDeviceMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("defaultTransactionManager")
@RequiredArgsConstructor
public class AppDeviceService {

    private final AppDeviceRepository appDeviceRepository;
    private final AppDeviceMapper appDeviceMapper;

    /**
     * appdevice 조회 by uuid
     */
    public Optional<AppDeviceDTO> findOneByUuid(String uuid) {
        log.debug("Request to findOneByUuid uuid : {}", uuid);
        return appDeviceRepository.findOneByUuid(uuid).map(appDeviceMapper::toDto);
    }

    /**
     * appdevice 저장
     */
    public AppDeviceDTO save(AppDeviceDTO appDeviceDTO) {
        log.debug("Request to save appDevice : {}", appDeviceDTO);
        AppDevice appDevice = appDeviceMapper.toEntity(appDeviceDTO);
        appDevice = appDeviceRepository.save(appDevice);
        return appDeviceMapper.toDto(appDevice);
    }

    /**
     * appdevice 조회 by 사용자ID
     */
    public Optional<AppDeviceDTO> findFirstByUserIdOrderByCreatedBy(Long userId) {
        log.debug("Request to findFirstByUserIdOrderByCreatedBy userId : {}", userId);
        return appDeviceRepository.findFirstByUserIdOrderByCreatedBy(userId).map(appDeviceMapper::toDto);
    }
  
}
