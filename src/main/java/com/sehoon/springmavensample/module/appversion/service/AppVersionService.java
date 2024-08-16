package com.sehoon.springmavensample.module.appversion.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sehoon.springmavensample.module.appversion.repository.AppVersionRepository;
import com.sehoon.springmavensample.module.appversion.repository.AppVersionRepositoryCustom;
import com.sehoon.springmavensample.module.appversion.service.dto.AppVersionDTO;
import com.sehoon.springmavensample.module.appversion.service.mapper.AppVersionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional("defaultTransactionManager")
@RequiredArgsConstructor
public class AppVersionService {

    private final AppVersionRepository appVersionRepository;
    private final AppVersionRepositoryCustom appVersionRepositoryCustom;
    private final AppVersionMapper appVersionMapper;

    /**
     * osCode로 최신 버전 조회
     * @param osCode
     * @return
     */
    public Optional<AppVersionDTO> findByOsCode(String osCode) {
        log.debug("Request to findByOsCode osCode : {}", osCode);
        return appVersionRepository.findFirstByOsCodeOrderByVersionDesc(osCode).map(appVersionMapper::toDto);
    }

    /**
     * osCode로 강제 업데이트 버전 조회
     * @param osCode
     * @return
     */
    public Optional<AppVersionDTO> findForceUpdateVersionByOsCode(String osCode) {
        return appVersionRepositoryCustom.findByOsCodeAndIsForceUpdate(osCode).map(appVersionMapper::toDto);
    }

}
