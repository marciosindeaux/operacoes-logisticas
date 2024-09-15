package br.com.logistic.operations.user_service.transportlayers.controllers.response

import java.util.*

data class UserRegisterResponse (
    val id: UUID,
    val email: String,
    val category: String
)