package com.patronus.deviceownership.testutil

import com.patronus.deviceownership.device.entity.DeviceDto
import com.patronus.deviceownership.device.entity.DeviceEntity
import java.util.UUID

object DeviceFixture {
    fun createDeviceDto(deviceId: UUID? = DEVICE_ID, phoneNumber: String = PHONE_NUMBER): DeviceDto {
        return DeviceDto(
            deviceId,
            SERIAL_NUMBER,
            MODEL,
            phoneNumber
        )
    }

    fun createDeviceEntity(deviceId: UUID? = DEVICE_ID): DeviceEntity {
        val deviceEntity = DeviceEntity()
        deviceEntity.id = deviceId
        deviceEntity.serialNumber = SERIAL_NUMBER
        deviceEntity.model = MODEL
        deviceEntity.phoneNumber = PHONE_NUMBER
        return deviceEntity
    }

    private const val SERIAL_NUMBER = "GHRHQ1VLQ05R"
    private const val MODEL = "Ventura 13.4.1"
    private const val PHONE_NUMBER = "+491751122343"
    val DEVICE_ID: UUID = UUID.randomUUID()
}