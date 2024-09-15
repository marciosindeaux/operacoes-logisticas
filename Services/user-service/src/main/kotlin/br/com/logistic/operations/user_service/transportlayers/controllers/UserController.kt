package br.com.logistic.operations.user_service.transportlayers.controllers

import br.com.logistic.operations.user_service.commons.enums.UserCategoryEnum
import br.com.logistic.operations.user_service.core.interactors.UserUseCase
import br.com.logistic.operations.user_service.transportlayers.controllers.request.UserRegisterRequest
import br.com.logistic.operations.user_service.transportlayers.controllers.response.UserRegisterResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController (
    val userService: UserUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveNewUser( @RequestBody @Valid  userRegister: UserRegisterRequest) : ResponseEntity<Any> {
        val savedUser = userService.createNewUser(userRegister.toDTO())
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @PostMapping("{userId}/category/{newCategory}")
    fun updateCategory(
        @PathVariable("userId") userId: String,
        @PathVariable("newCategory") userCategory: String) : ResponseEntity<Any> {
        val savedCategoryUser = userService.updateCategoryFromUser(userId, userCategory)
        return ResponseEntity.ok(savedCategoryUser)
    }

}