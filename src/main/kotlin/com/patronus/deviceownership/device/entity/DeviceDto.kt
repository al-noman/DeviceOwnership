package com.patronus.deviceownership.device.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.patronus.deviceownership.device.control.PhoneNumberValidator
import java.util.UUID

data class DeviceDto(
    @get:JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var id: UUID?,
    val serialNumber: String,
    val model: String,
    @field:PhoneNumberValidator
    val phoneNumber: String
)
