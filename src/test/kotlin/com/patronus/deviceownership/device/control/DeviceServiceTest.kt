package com.patronus.deviceownership.device.control

import com.patronus.deviceownership.device.entity.DeviceRepository
import com.patronus.deviceownership.testutil.DeviceFixture.createDeviceEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class DeviceServiceTest {
    @Mock
    private lateinit var deviceRepository: DeviceRepository
    @InjectMocks
    private lateinit var deviceService: DeviceService

    @Test
    fun `should delegate to device repository to create device`() {
        val deviceEntity = createDeviceEntity()
        `when`(deviceRepository.save(deviceEntity)).thenReturn(deviceEntity)

        val persistedDeviceEntity = deviceService.createDevice(deviceEntity)

        assertThat(persistedDeviceEntity).isEqualTo(deviceEntity)
        verify(deviceRepository).save(deviceEntity)
    }

    @Test
    fun `should retrieve all device entities by delegating to device repository`() {
        val devices = listOf(createDeviceEntity(), createDeviceEntity().apply { id = UUID.randomUUID() })
        `when`(deviceRepository.findAll()).thenReturn(devices)

        val persistedDevices = deviceService.getDevices()

        assertThat(persistedDevices).containsExactlyInAnyOrderElementsOf(devices)
        verify(deviceRepository).findAll()
    }
}