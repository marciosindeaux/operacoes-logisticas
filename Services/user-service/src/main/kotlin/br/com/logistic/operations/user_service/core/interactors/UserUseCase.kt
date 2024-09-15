package br.com.logistic.operations.user_service.core.interactors

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.commons.exceptions.InvalidOperationException
import br.com.logistic.operations.user_service.commons.objects.Messages
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTO
import br.com.logistic.operations.user_service.core.repository.UserRepository
import br.com.logistic.operations.user_service.datasources.UserRepositoryImpl
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserUseCase(
    private val userRepositoryImpl: UserRepository
) {

    fun createNewUser(newUser: UserRegisterDTO) : UserRegisterDTO {
        val sameEmailUsers = userRepositoryImpl.findByEmail(newUser.email)

        if(sameEmailUsers.isNotEmpty()) {
            throw InvalidOperationException(Messages.Errors.USED_EMAIL.format(newUser.email))
        }

        return userRepositoryImpl.saveUser(newUser.toEntity()).toDTO()
    }

    fun updateCategoryFromUser(userId: String, newCategory: String ) : UserRegisterDTO {

        val category = UserCategoryEnum.getCategory(newCategory)
            ?: throw InvalidOperationException(Messages.Errors.INVALID_CATEGORY.format(newCategory))

        val user = userRepositoryImpl.findById(userId)
            ?: throw InvalidOperationException(Messages.Errors.INVALID_USER_ID.format(userId));

        user.category = category
        return userRepositoryImpl.saveUser(user).toDTO()

    }
}