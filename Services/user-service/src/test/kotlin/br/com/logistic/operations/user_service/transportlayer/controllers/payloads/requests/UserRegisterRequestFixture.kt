package br.com.logistic.operations.user_service.transportlayer.controllers.payloads.requests

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.transportlayers.controllers.request.UserRegisterRequest

class UserRegisterRequestFixture {

    companion object {
        private fun userRegisterRequest(name: String, email: String, type: UserCategoryEnum) = UserRegisterRequest(
            name,
            email,
            type
        )

        fun defaultUserRegisterRequest() = userRegisterRequest(
            "User Name",
            "userEmail@mail.com",
            UserCategoryEnum.DEFAULT_USER
        )
    }

}