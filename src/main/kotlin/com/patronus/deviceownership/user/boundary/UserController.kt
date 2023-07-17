package com.patronus.deviceownership.user.boundary

import com.patronus.deviceownership.user.control.UserService
import com.patronus.deviceownership.user.entity.UserDto
import com.patronus.deviceownership.user.entity.UserEntity
import com.patronus.deviceownership.user.entity.UserMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
    val userMapper: UserMapper
) {
    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        val userEntity: UserEntity = userService.createUser(userMapper.toEntity(userDto))
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(userEntity))
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        val users: List<UserEntity> = userService.findAll()
        return ResponseEntity.ok().body(userMapper.toDtoList(users))
    }
}