package br.com.logistic.operations.user_service.datasources

import br.com.logistic.operations.user_service.core.entities.UserEntity
import br.com.logistic.operations.user_service.core.repository.UserRepository
import br.com.logistic.operations.user_service.datasources.daos.UserDatasource
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRepositoryImpl(
    private val userDatasource: UserDatasource
): UserRepository {
    override fun findByEmail(email: String): List<UserEntity> {
        return userDatasource.findAllByEmail(email)
    }

    override fun saveUser(user: UserEntity) : UserEntity {
        return userDatasource.saveAndFlush(user)
    }

    override fun findById(uuid: String): UserEntity? {
        return userDatasource.findById(UUID.fromString(uuid)).get()
    }
}