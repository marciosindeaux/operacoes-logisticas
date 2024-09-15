package br.com.logistic.operations.user_service.transportlayers.controllers.request

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.core.dtos.UserRegisterDTO
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRegisterRequest (

    @field:NotNull
    @field:NotBlank(message = "Name is mandatory")
    val name: String,

    @field:NotNull
    @field:Email(message = "Has to be a valid Email")
    @field:NotBlank(message = "Email is mandatory")
    val email: String,

    @field:NotNull
    val category:UserCategoryEnum,
) {

    fun toDTO(): UserRegisterDTO = UserRegisterDTO(
        null,
        this.name,
        this.email,
        this.category
    )

}