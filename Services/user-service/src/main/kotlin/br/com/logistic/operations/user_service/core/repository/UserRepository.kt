package br.com.logistic.operations.user_service.core.repository

import br.com.logistic.operations.user_service.core.entities.UserEntity

import org.springframework.stereotype.Component


interface UserRepository {

    fun findByEmail(email: String): List<UserEntity>

    fun saveUser(user: UserEntity) : UserEntity

    fun findById(uuid: String): UserEntity?
}