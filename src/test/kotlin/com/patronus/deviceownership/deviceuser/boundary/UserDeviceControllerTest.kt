package com.patronus.deviceownership.deviceuser.boundary

import com.patronus.deviceownership.deviceuser.control.UserDeviceService
import com.patronus.deviceownership.testutil.DeviceFixture.DEVICE_ID
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceDto
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceEntity
import com.patronus.deviceownership.testutil.UserFixture.USER_ID
import com.patronus.deviceownership.testutil.UserFixture.createUserDto
import com.patronus.deviceownership.testutil.UserFixture.createUserEntity
import com.patronus.deviceownership.user.entity.UserMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class UserDeviceControllerTest {
    @Mock
    private lateinit var userDeviceService: UserDeviceService
    @Mock
    private lateinit var userMapper: UserMapper
    @InjectMocks
    private lateinit var userDeviceController: UserDeviceController

    @Test
    fun `should assign a device to a user and respond with http status ok`() {
        val deviceDto = createDeviceDto(DEVICE_ID)
        val deviceEntity = createDeviceEntity(DEVICE_ID)
        val userDto = createUserDto(USER_ID).apply { this.device = deviceDto }
        val userEntity = createUserEntity(USER_ID).apply { this.device = deviceEntity }

        `when`(userDeviceService.assignDeviceToUser(USER_ID, DEVICE_ID)).thenReturn(userEntity)
        `when`(userMapper.toDto(userEntity)).thenReturn(userDto)

        val result = userDeviceController.assignDeviceToUser(USER_ID, DEVICE_ID)

        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body).isEqualTo(userDto)
        verify(userDeviceService).assignDeviceToUser(USER_ID, DEVICE_ID)
        verify(userMapper).toDto(userEntity)
    }
}