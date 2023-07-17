package com.patronus.deviceownership.user.control

import com.patronus.deviceownership.user.entity.UserEntity
import com.patronus.deviceownership.user.entity.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun createUser(userEntity: UserEntity) = userRepository.save(userEntity)
    fun findAll(): List<UserEntity> = userRepository.findAll()
}
