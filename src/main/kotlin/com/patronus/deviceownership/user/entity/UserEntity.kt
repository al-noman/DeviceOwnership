package com.patronus.deviceownership.user.entity

import com.patronus.deviceownership.device.entity.DeviceEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "USERS")
class UserEntity {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(nullable = false)
    var id: UUID? = null

    var firstName: String = ""

    var lastName: String = ""

    var address: String = ""

    var birthDay: LocalDate = LocalDate.EPOCH

    @OneToOne
    @JoinColumn(name = "DEVICE_ID", referencedColumnName = "ID")
    var device: DeviceEntity? = null
}
