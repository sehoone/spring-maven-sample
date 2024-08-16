package com.sehoon.springmavensample.module.appdevice.service.mapper;

import org.mapstruct.Mapper;

import com.sehoon.springmavensample.common.service.mapper.EntityMapper;
import com.sehoon.springmavensample.module.appdevice.domain.AppDevice;
import com.sehoon.springmavensample.module.appdevice.service.dto.AppDeviceDTO;

@Mapper(componentModel="spring", uses={})
public interface AppDeviceMapper extends EntityMapper<AppDeviceDTO, AppDevice> {
}
