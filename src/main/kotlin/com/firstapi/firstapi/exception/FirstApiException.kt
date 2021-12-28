package com.firstapi.firstapi.exception

import com.firstapi.firstapi.user.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class FirstApiException : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [Exception::class])
    fun handleServletRequestBindingException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        val exception = ExceptionResponse(Date(), ex.message ?: "", request.getDescription(false))
        return ResponseEntity(exception, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [UserNotFoundException::class])
    fun handleUserNotFoundException(ex: UserNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val exception = ExceptionResponse(Date(), ex.message ?: "", request.getDescription(false))
        return ResponseEntity(exception, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val exception = ExceptionResponse(Date(), "Validation Failed", ex.bindingResult.toString())
        return ResponseEntity(exception, HttpStatus.BAD_REQUEST)
    }
}