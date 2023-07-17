package com.patronus.deviceownership.device.control

import com.patronus.deviceownership.device.entity.DeviceEntity
import com.patronus.deviceownership.device.entity.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(val deviceRepository: DeviceRepository) {
    fun createDevice(deviceEntity: DeviceEntity): DeviceEntity = deviceRepository.save(deviceEntity)
    fun getDevices(): List<DeviceEntity> = deviceRepository.findAll()
}
