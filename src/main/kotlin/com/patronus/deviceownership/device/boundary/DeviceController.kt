package com.patronus.deviceownership.device.boundary

import com.patronus.deviceownership.device.control.DeviceService
import com.patronus.deviceownership.device.entity.DeviceDto
import com.patronus.deviceownership.device.entity.DeviceEntity
import com.patronus.deviceownership.device.entity.DeviceMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/devices")
class DeviceController(
    val deviceService: DeviceService,
    val deviceMapper: DeviceMapper
) {
    @PostMapping
    fun createDevice(@Validated @RequestBody deviceDto: DeviceDto): ResponseEntity<DeviceDto> {
        val deviceEntity = deviceService.createDevice(
            deviceMapper.toEntity(deviceDto)
        )
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                deviceMapper.toDto(deviceEntity)
            )
    }

    @GetMapping
    fun getAllDevice(): ResponseEntity<List<DeviceDto>> {
        val devices: List<DeviceEntity> = deviceService.getDevices()
        return ResponseEntity.ok(deviceMapper.toDtoList(devices))
    }
}