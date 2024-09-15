package br.com.logistic.operations.user_service.core.entities

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "TB_PERSON")
class UserEntity : PersistentObject() {

    @Column(name ="NAME", nullable = false)
    lateinit var name: String;

    @Column(name ="EMAIL", nullable = false)
    lateinit var email: String;

    @Column(name ="CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var category: UserCategoryEnum;


    fun toDTO() = UserRegisterDTO(
        id = id.toString(),
        name = name,
        email = email,
        userCategory =  category
    )
}