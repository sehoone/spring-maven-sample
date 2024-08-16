package com.sehoon.springmavensample.module.user.service.mapper;

import org.mapstruct.Mapper;

import com.sehoon.springmavensample.common.service.mapper.EntityMapper;
import com.sehoon.springmavensample.module.user.domain.User;
import com.sehoon.springmavensample.module.user.service.dto.UserDTO;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
