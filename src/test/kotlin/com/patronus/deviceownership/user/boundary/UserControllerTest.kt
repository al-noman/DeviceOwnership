package com.patronus.deviceownership.user.boundary

import com.patronus.deviceownership.testutil.UserFixture.createUserDto
import com.patronus.deviceownership.testutil.UserFixture.createUserEntity
import com.patronus.deviceownership.user.control.UserService
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
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class UserControllerTest {
    @Mock
    private lateinit var userService: UserService
    @Mock
    private lateinit var userMapper: UserMapper
    @InjectMocks
    private lateinit var userController: UserController

    @Test
    fun `should create user and respond with http status created`() {
        val userDto = createUserDto()
        val userEntity = createUserEntity()

        `when`(userMapper.toEntity(userDto)).thenReturn(userEntity)
        `when`(userMapper.toDto(userEntity)).thenReturn(userDto)
        `when`(userService.createUser(userEntity)).thenReturn(userEntity)

        val result = userController.createUser(userDto)

        assertThat(result.body).isEqualTo(userDto)
        assertThat(result.statusCode).isEqualTo(HttpStatus.CREATED)
        verify(userMapper).toDto(userEntity)
        verify(userMapper).toEntity(userDto)
        verify(userService).createUser(userEntity)
    }

    @Test
    fun `should retrieve all users and respond with http status ok`() {
        val userDtos = listOf(createUserDto(), createUserDto().apply { id = UUID.randomUUID() })
        val userEntities = listOf(createUserEntity(), createUserEntity().apply { id = UUID.randomUUID() })

        `when`(userMapper.toDtoList(userEntities)).thenReturn(userDtos)
        `when`(userService.findAll()).thenReturn(userEntities)

        val result = userController.getAllUsers()

        assertThat(result.body).hasSize(2)
        assertThat(result.body).containsExactlyInAnyOrderElementsOf(userDtos)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        verify(userMapper).toDtoList(userEntities)
        verify(userService).findAll()
    }
}