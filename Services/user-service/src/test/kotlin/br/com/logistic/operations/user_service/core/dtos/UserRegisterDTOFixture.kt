package br.com.logistic.operations.user_service.core.dtos

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import java.util.*

class UserRegisterDTOFixture {

    companion object {
        private fun userRegisterDTO(id: String?, name: String, email: String, type: UserCategoryEnum) = UserRegisterDTO(
            id,
            name,
            email,
            type
        )

        fun defaultNewUserRegisterDTO() = userRegisterDTO(
            null,
            "User Name",
            "user_email@email.com",
            UserCategoryEnum.DEFAULT_USER
        )

        fun defaultRegistredUserDTO() = userRegisterDTO(
            UUID.randomUUID().toString(),
            "User Name",
            "user_email@email.com",
            UserCategoryEnum.DEFAULT_USER
        )
    }
}