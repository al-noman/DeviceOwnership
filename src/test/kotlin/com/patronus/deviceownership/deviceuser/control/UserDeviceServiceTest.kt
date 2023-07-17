package com.patronus.deviceownership.deviceuser.control

import com.patronus.deviceownership.device.entity.DeviceRepository
import com.patronus.deviceownership.testutil.DeviceFixture.DEVICE_ID
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceEntity
import com.patronus.deviceownership.testutil.UserFixture.USER_ID
import com.patronus.deviceownership.testutil.UserFixture.createUserEntity
import com.patronus.deviceownership.user.entity.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class UserDeviceServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var deviceRepository: DeviceRepository
    @InjectMocks
    private lateinit var userDeviceService: UserDeviceService

    @Test
    fun `should find both user and device and assign device to user`() {
        val persistedUserWithoutDevice = createUserEntity(USER_ID)
        val persistedDevice = createDeviceEntity(DEVICE_ID)
        val persistedUserWithDevice = persistedUserWithoutDevice.apply { this.device = persistedDevice }

        Mockito.`when`(userRepository.findById(USER_ID)).thenReturn(Optional.of(persistedUserWithoutDevice))
        Mockito.`when`(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.of(persistedDevice))
        Mockito.`when`(userRepository.save(persistedUserWithDevice)).thenReturn(persistedUserWithDevice)

        val userEntity = userDeviceService.assignDeviceToUser(USER_ID, DEVICE_ID)

        assertThat(userEntity).isEqualTo(persistedUserWithDevice)
        verify(userRepository).save(persistedUserWithDevice)
    }

    @Test
    fun `should throw entity not found exception when user is not found`() {
        Mockito.`when`(userRepository.findById(USER_ID)).thenReturn(Optional.empty())

        assertThatThrownBy { userDeviceService.assignDeviceToUser(USER_ID, DEVICE_ID) }
            .isInstanceOf(EntityNotFoundException::class.java)
            .hasMessage("User not found with id: $USER_ID")
    }

    @Test
    fun `should throw entity not found exception when device is not found`() {
        Mockito.`when`(userRepository.findById(USER_ID)).thenReturn(Optional.of(createUserEntity(USER_ID)))
        Mockito.`when`(deviceRepository.findById(DEVICE_ID)).thenReturn(Optional.empty())

        assertThatThrownBy { userDeviceService.assignDeviceToUser(USER_ID, DEVICE_ID) }
            .isInstanceOf(EntityNotFoundException::class.java)
            .hasMessage("Device not found with id: $DEVICE_ID")
    }
}