package com.patronus.deviceownership.deviceuser.boundary

import com.patronus.deviceownership.deviceuser.control.UserDeviceService
import com.patronus.deviceownership.user.entity.UserDto
import com.patronus.deviceownership.user.entity.UserMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserDeviceController(
    val userDeviceService: UserDeviceService,
    val userMapper: UserMapper
) {
    @PostMapping("/assign-device-to-user")
    fun assignDeviceToUser(
        @RequestParam("userId") userId: UUID,
        @RequestParam("deviceId") deviceId: UUID
    ): ResponseEntity<UserDto> {
        val userEntity = userDeviceService.assignDeviceToUser(userId, deviceId)
        return ResponseEntity.ok(userMapper.toDto(userEntity))
    }
}