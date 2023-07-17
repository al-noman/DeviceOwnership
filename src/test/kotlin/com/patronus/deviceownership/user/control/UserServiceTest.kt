package com.patronus.deviceownership.user.control

import com.patronus.deviceownership.testutil.UserFixture.createUserEntity
import com.patronus.deviceownership.user.entity.UserRepository
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
class UserServiceTest {
    @Mock
    private lateinit var userRepository: UserRepository
    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun `should save user and delegate to repository`() {
        val userEntity = createUserEntity()
        `when`(userRepository.save(userEntity)).thenReturn(userEntity)

        val persistedUser = userService.createUser(userEntity)

        assertThat(persistedUser).isEqualTo(userEntity)
        verify(userRepository).save(userEntity)
    }

    @Test
    fun `should get all users by delegating to the repository`() {
        val userEntities = listOf(createUserEntity(), createUserEntity().apply { id = UUID.randomUUID() })
        `when`(userRepository.findAll()).thenReturn(userEntities)

        val persistedUsers = userService.findAll()

        assertThat(persistedUsers).hasSize(2)
        assertThat(persistedUsers).containsExactlyInAnyOrderElementsOf(userEntities)
        verify(userRepository).findAll()
    }
}