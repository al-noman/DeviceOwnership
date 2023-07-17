package com.patronus.deviceownership.user.entity

import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toEntity(userDto: UserDto): UserEntity
    fun toDto(userEntity: UserEntity): UserDto
    fun toDtoList(users: List<UserEntity>) = users.map { toDto(it) }.toList()
}
