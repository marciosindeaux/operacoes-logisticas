package br.com.logistic.operations.user_service.core.entities

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.transportlayers.controllers.request.UserRegisterRequest
import org.apache.catalina.User
import java.util.*

class UserEntityFixture {

    companion object {
        private fun userEntity(name: String, email: String, type: UserCategoryEnum, userId: UUID? = null) : UserEntity {
            val userEntity = UserEntity()
            userEntity.category = type
            userEntity.email = email
            userEntity.name = name
            userEntity.id = userId ?: UUID.randomUUID()
            return userEntity
        }

        fun defaultNewUserEntity() = userEntity(
            "User Name",
            "user_email@mail.com",
            UserCategoryEnum.DEFAULT_USER
        )

        fun defaultNewUserEntityWithId(id: String) = userEntity(
            "User Name",
            "user_email@mail.com",
            UserCategoryEnum.DEFAULT_USER,
            UUID.fromString(id)
        )
    }
}