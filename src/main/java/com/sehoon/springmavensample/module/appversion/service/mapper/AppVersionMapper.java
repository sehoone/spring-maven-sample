package com.sehoon.springmavensample.module.appversion.service.mapper;

import org.mapstruct.Mapper;

import com.sehoon.springmavensample.common.service.mapper.EntityMapper;
import com.sehoon.springmavensample.module.appversion.domain.AppVersion;
import com.sehoon.springmavensample.module.appversion.service.dto.AppVersionDTO;

@Mapper(componentModel = "spring", uses = {})
public interface AppVersionMapper extends EntityMapper<AppVersionDTO, AppVersion> {
}
