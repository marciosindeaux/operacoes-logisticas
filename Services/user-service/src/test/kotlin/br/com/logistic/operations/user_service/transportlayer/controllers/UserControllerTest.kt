package br.com.logistic.operations.user_service.transportlayer.controllers

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTO
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTOFixture
import br.com.logistic.operations.user_service.core.interactors.UserUseCase
import br.com.logistic.operations.user_service.transportlayer.controllers.payloads.requests.UserRegisterRequestFixture
import br.com.logistic.operations.user_service.transportlayers.controllers.UserController
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertEquals

class UserControllerTest {
    private val userUseCase: UserUseCase = mockk()
    private val userController: UserController = UserController(userUseCase)

    @Test
    fun `should be saved a new user`() {
        every { userUseCase.createNewUser(any()) } returns UserRegisterDTOFixture.defaultNewUserRegisterDTO()

        val response = userController.saveNewUser(UserRegisterRequestFixture.defaultUserRegisterRequest())

        verify (exactly = 1) { userUseCase.createNewUser(any()) }
        assertEquals(HttpStatus.CREATED.value(), response.statusCode.value())
    }

    @Test
    fun `should be update user category`(){
        val registredUser = UserRegisterDTOFixture.defaultRegistredUserDTO().also { it.userCategory =  UserCategoryEnum.INTERNAL}
        every { userUseCase.updateCategoryFromUser(any(), any()) } returns registredUser

        val response = userController.updateCategory(registredUser.id!!, UserCategoryEnum.INTERNAL.name)

        assertEquals(registredUser.id, (response.body as UserRegisterDTO).id)
        assertEquals(registredUser.userCategory.name, (response.body as UserRegisterDTO).userCategory.name)
    }

}