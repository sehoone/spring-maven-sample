package com.sehoon.springmavensample.module.appversion.repository;

import static com.sehoon.springmavensample.module.appversion.domain.QAppVersion.appVersion;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sehoon.springmavensample.module.appversion.domain.AppVersion;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AppVersionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    /**
     * 강업정보 조회 by osCode
     * @param osCode
     * @return
     */
    public Optional<AppVersion> findByOsCodeAndIsForceUpdate(String osCode) {
        return Optional.ofNullable(queryFactory.selectFrom(appVersion)
                .where(appVersion.osCode.eq(osCode).and(appVersion.isForceUpdate.eq(true))).fetchOne());
    }

}
