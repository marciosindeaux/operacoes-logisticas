package br.com.logistic.operations.user_service.datasources.daos

import br.com.logistic.operations.user_service.core.entities.UserEntity
import org.apache.catalina.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserDatasource: JpaRepository<UserEntity, UUID> {

    fun findAllByEmail(email: String) : List<UserEntity>

}