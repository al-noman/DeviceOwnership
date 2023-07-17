package com.patronus.deviceownership.device.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Table(name = "DEVICES")
class DeviceEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(nullable = false)
    var id: UUID? = null

    var serialNumber: String = ""

    var model: String = ""

    var phoneNumber: String = ""
}
