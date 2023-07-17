package com.patronus.deviceownership.testutil

import com.patronus.deviceownership.user.entity.UserDto
import com.patronus.deviceownership.user.entity.UserEntity
import java.time.LocalDate
import java.util.UUID

object UserFixture {
    fun createUserDto(userId: UUID? = null): UserDto {
        return UserDto(
            userId,
            FIRST_NAME,
            LAST_NAME,
            ADDRESS,
            BIRTH_DAY,
            null
        )
    }

    fun createUserEntity(userId: UUID? = null): UserEntity {
        val userEntity = UserEntity()
        userEntity.id = userId
        userEntity.firstName = FIRST_NAME
        userEntity.lastName = LAST_NAME
        userEntity.address = ADDRESS
        userEntity.birthDay = BIRTH_DAY
        userEntity.device = null
        return userEntity
    }

    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ADDRESS = "ADDRESS"
    private val BIRTH_DAY: LocalDate = LocalDate.of(1945, 6, 15)
    val USER_ID: UUID = UUID.randomUUID()
}