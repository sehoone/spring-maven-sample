package com.sehoon.springmavensample.module.user.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sehoon.springmavensample.module.user.service.dto.UserAppDeviceDTO;

import lombok.RequiredArgsConstructor;

import static com.sehoon.springmavensample.module.user.domain.QUser.user;
import static com.sehoon.springmavensample.module.appdevice.domain.QAppDevice.appDevice;

@RequiredArgsConstructor
@Repository
public class UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public Optional<UserAppDeviceDTO> findUserAppdeviceByUserId(Long userId) {

        return Optional.ofNullable(queryFactory.select(Projections.constructor(UserAppDeviceDTO.class,
        user.id, user.account, user.password, user.name, user.emailAddr, user.phoneNumber, user.engName, user.departmentName, user.positionName,
        user.profileUrl, appDevice.id, appDevice.uuid, appDevice.osCode, appDevice.appVersion, appDevice.deviceInfo))
                .from(user)
                .innerJoin(appDevice).on(user.id.eq(appDevice.userId))
                .where(user.id.eq(userId).and(appDevice.isDeleted.eq(false))).fetchOne());

    }

    public Optional<UserAppDeviceDTO> findUserAppdeviceByAccount(String userAccount) {

        return Optional.ofNullable(queryFactory.select(Projections.constructor(UserAppDeviceDTO.class,
        user.id, user.account, user.password, user.name, user.emailAddr, user.phoneNumber, user.engName, user.departmentName, user.positionName,
        user.profileUrl, appDevice.id, appDevice.uuid, appDevice.osCode, appDevice.appVersion, appDevice.deviceInfo))
                .from(user)
                .innerJoin(appDevice).on(user.id.eq(appDevice.userId))
                .where(user.account.eq(userAccount).and(appDevice.isDeleted.eq(false))).fetchOne());

    }

}
