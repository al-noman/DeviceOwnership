package com.patronus.deviceownership.deviceuser.control

import com.patronus.deviceownership.device.entity.DeviceRepository
import com.patronus.deviceownership.user.entity.UserEntity
import com.patronus.deviceownership.user.entity.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserDeviceService(
    val userRepository: UserRepository,
    val deviceRepository: DeviceRepository
) {
    fun assignDeviceToUser(userId: UUID, deviceId: UUID): UserEntity {
        val user = userRepository.findByIdOrNull(userId) ?: throw EntityNotFoundException("User not found with id: $userId")
        val device = deviceRepository.findByIdOrNull(deviceId) ?: throw EntityNotFoundException("Device not found with id: $deviceId")
        user.device = device

        return userRepository.save(user)
    }

}
