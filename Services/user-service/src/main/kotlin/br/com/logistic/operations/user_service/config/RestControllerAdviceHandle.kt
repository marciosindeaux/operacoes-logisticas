package br.com.logistic.operations.user_service.config

import br.com.logistic.operations.user_service.commons.exceptions.InvalidOperationException
import br.com.logistic.operations.user_service.transportlayers.controllers.response.ErrorResponse
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class RestControllerAdviceHandle {

    companion object  {
        val logger: Log = LogFactory.getLog(RestControllerAdviceHandle::class.java)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        return buildErrorCodeResponse(
            HttpStatus.BAD_REQUEST,
            ErrorCodes.BAD_REQUEST,
            ex.bindingResult.allErrors.map { it.defaultMessage }.joinToString(),
            "Validator Exception, use valid values"
        ).also {
            logError(ex, ErrorCodes.BAD_REQUEST)
        }
    }

    @ExceptionHandler(value = [Exception::class])
    fun genericHandleException(ex: Exception): ResponseEntity<Any> {
        return buildErrorCodeResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ErrorCodes.INTERNAL_SERVER_ERROR,
            null, ex.message
        ).also {
            logError(ex, ErrorCodes.INTERNAL_SERVER_ERROR)
        }
    }

    @ExceptionHandler(value = [InvalidOperationException::class])
    fun invalidOperationHandleException(ex:  InvalidOperationException): ResponseEntity<Any> {
        return buildErrorCodeResponse(
            HttpStatus.NOT_ACCEPTABLE,
            ErrorCodes.NOT_ACCEPTABLE,
            null, ex.message
        ).also {
            logError(ex, ErrorCodes.NOT_ACCEPTABLE)
        }
    }


    private fun buildErrorCodeResponse(
        httpStatus: HttpStatus,
        errorCode: ErrorCodes,
        message: String?,
        detailedMessage: String?) : ResponseEntity<Any> {

        return ResponseEntity
            .status(httpStatus)
            .body(
                ErrorResponse(
                    errorCode.name,
                    message ?: errorCode.text,
                    detailedMessage
                )
            )
    }

    private fun logError(cause: Exception, error: ErrorCodes) {
        val message = cause.message ?: "Exception without message"
        logger.error { "${error.name} - Rest Exception with the following messages: $message" }
    }

    enum class ErrorCodes(val text: String) {
        INTERNAL_SERVER_ERROR("Internal Server Error"),
        BAD_REQUEST("Bad Request"),
        NOT_ACCEPTABLE("Not Acceptable")
    }
}