package com.patronus.deviceownership.user.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.patronus.deviceownership.device.entity.DeviceDto
import java.time.LocalDate
import java.util.UUID

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserDto(
    @get:JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: UUID?,
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthDay: LocalDate,
    var device: DeviceDto?
)
