package com.sehoon.springmavensample.module.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sehoon.springmavensample.module.user.domain.User;
import com.sehoon.springmavensample.module.user.repository.UserRepository;
import com.sehoon.springmavensample.module.user.repository.UserRepositoryCustom;
import com.sehoon.springmavensample.module.user.service.dto.UserAppDeviceDTO;
import com.sehoon.springmavensample.module.user.service.dto.UserDTO;
import com.sehoon.springmavensample.module.user.service.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service Implementation for managing {@link User}.
 */
@Slf4j
@Service
@Transactional("defaultTransactionManager")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public Optional<UserDTO> findOneByAccount(String account) {
        Optional<UserDTO> userDTO = userRepository.findOneByAccount(account).map(userMapper::toDto);
        log.debug("계정 존재 확인 : {}",userDTO);
        return userDTO;
    }
    
    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(Long id) {
        Optional<UserDTO> userDTO = userRepository.findById(id).map(userMapper::toDto);
        log.debug("계정 존재 확인 : {}",userDTO);
        return userDTO;
    }

    @Transactional(readOnly = true)
    public Optional<UserAppDeviceDTO> findUserAppdeviceByUserId(Long userId) {
        Optional<UserAppDeviceDTO> userAppDeviceDTO = userRepositoryCustom.findUserAppdeviceByUserId(userId);
        log.debug("계정디바이스 존재 확인 : {}", userAppDeviceDTO);
        return userAppDeviceDTO;
    }
}
