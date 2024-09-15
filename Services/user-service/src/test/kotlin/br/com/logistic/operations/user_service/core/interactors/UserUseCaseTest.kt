package br.com.logistic.operations.user_service.core.interactors

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.commons.exceptions.InvalidOperationException
import br.com.logistic.operations.user_service.commons.objects.Messages
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTO
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTOFixture
import br.com.logistic.operations.user_service.core.entities.UserEntity
import br.com.logistic.operations.user_service.core.entities.UserEntityFixture
import br.com.logistic.operations.user_service.core.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class UserUseCaseTest {

    private val userRepositoryImpl: UserRepository = mockk()
    private val userUseCase: UserUseCase = UserUseCase(userRepositoryImpl)

    @Test
    fun `should create a new user`() {
        val newUser = UserRegisterDTOFixture.defaultNewUserRegisterDTO()

        every { userRepositoryImpl.findByEmail(any()) } returns listOf()
        every { userRepositoryImpl.saveUser(any()) } returns newUser.toEntity()

        val response = userUseCase.createNewUser(newUser)

        assertEquals(newUser.email, response.email)
        assertEquals(newUser.name, response.name)
        assertEquals(newUser.userCategory.name, response.userCategory.name)

    }

    @Test
    fun `should not create a new user with used email`() {
        val newUser = UserRegisterDTOFixture.defaultNewUserRegisterDTO()

        every { userRepositoryImpl.findByEmail(any()) } returns listOf(UserEntityFixture.defaultNewUserEntity())

        try {
            userUseCase.createNewUser(newUser)
            assertTrue(false)
        }catch (ex: InvalidOperationException) {
            assertTrue(true)
        }

    }

    @Test
    fun `should not update category for user with invalid category`() {
        val invalidCategory = "invalid"
        try {
            userUseCase.updateCategoryFromUser(UUID.randomUUID().toString(), invalidCategory)
            assertTrue(false)
        }catch (ex: InvalidOperationException) {
            assertTrue(true)
            assertEquals(ex.message, Messages.Errors.INVALID_CATEGORY.format(invalidCategory))
        }
    }

    @Test
    fun `should not update category for user with unknow user`() {
        val validCategory = UserCategoryEnum.INTERNAL.name
        val unknownUserId = "userId"

        every { userRepositoryImpl.findById(unknownUserId) } returns null

        try {
            userUseCase.updateCategoryFromUser(unknownUserId, validCategory)
            assertTrue(false)
        }catch (ex: InvalidOperationException) {
            assertTrue(true)
            assertEquals(ex.message, Messages.Errors.INVALID_USER_ID.format(unknownUserId))
        }
    }

    @Test
    fun `should update category for user`() {
        val validCategory = UserCategoryEnum.INTERNAL
        val userId = UUID.randomUUID().toString()
        val returnedUser =  UserEntityFixture.defaultNewUserEntityWithId(userId).also { it.category = validCategory }

        every { userRepositoryImpl.findById(any()) } returns UserEntityFixture.defaultNewUserEntityWithId(userId)
        every { userRepositoryImpl.saveUser(any()) } returns returnedUser

        val response = userUseCase.updateCategoryFromUser(userId, validCategory.name)
        assertEquals(userId, response.id)
        assertEquals(validCategory.name, response.userCategory.name)
    }
}