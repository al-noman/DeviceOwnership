package com.patronus.deviceownership.device.boundary

import com.patronus.deviceownership.device.control.DeviceService
import com.patronus.deviceownership.device.entity.DeviceMapper
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceDto
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceEntity
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
class DeviceControllerTest {
    @Mock
    private lateinit var deviceService: DeviceService
    @Mock
    private lateinit var deviceMapper: DeviceMapper
    @InjectMocks
    private lateinit var deviceController: DeviceController

    @Test
    fun `should create device by delegating to device service and return http status created`() {
        val deviceDto = createDeviceDto()
        val deviceEntity = createDeviceEntity()

        `when`(deviceMapper.toEntity(deviceDto)).thenReturn(deviceEntity)
        `when`(deviceService.createDevice(deviceEntity)).thenReturn(deviceEntity)
        `when`(deviceMapper.toDto(deviceEntity)).thenReturn(deviceDto)

        val result = deviceController.createDevice(deviceDto)

        assertThat(result.body).isEqualTo(deviceDto)
        assertThat(result.statusCode).isEqualTo(HttpStatus.CREATED)
        verify(deviceService).createDevice(deviceEntity)
        verify(deviceMapper).toEntity(deviceDto)
        verify(deviceMapper).toDto(deviceEntity)
    }

    @Test
    fun `should delegate to service and return devices with http status ok`() {
        val deviceDtos = listOf(createDeviceDto())
        val deviceEntities = listOf(createDeviceEntity())

        `when`(deviceService.getDevices()).thenReturn(deviceEntities)
        `when`(deviceMapper.toDtoList(deviceEntities)).thenReturn(deviceDtos)

        val result = deviceController.getAllDevice()

        assertThat(result.body).containsExactlyInAnyOrderElementsOf(deviceDtos)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        verify(deviceService).getDevices()
        verify(deviceMapper).toDtoList(deviceEntities)
    }
}