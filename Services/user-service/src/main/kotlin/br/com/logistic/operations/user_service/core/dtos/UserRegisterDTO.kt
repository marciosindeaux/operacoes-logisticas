package br.com.logistic.operations.user_service.core.dtos

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.core.entities.UserEntity

data class UserRegisterDTO (
    val id: String? = null,
    val name: String,
    val email: String,
    var userCategory: UserCategoryEnum
) {

    fun toEntity() : UserEntity {
        val entity = UserEntity();
        entity.name = this.name;
        entity.category = this.userCategory;
        entity.email = this.email;
        return entity
    }
}