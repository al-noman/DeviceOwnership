package com.patronus.deviceownership.device.entity

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DeviceMapper {
    fun toDto(deviceEntity: DeviceEntity): DeviceDto
    fun toEntity(deviceDto: DeviceDto): DeviceEntity
    fun toDtoList(devices: List<DeviceEntity>): List<DeviceDto> {
        return devices.map { toDto(it) }.toList()
    }
}
