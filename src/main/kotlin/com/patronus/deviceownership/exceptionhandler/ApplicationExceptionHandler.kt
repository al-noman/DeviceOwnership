package com.patronus.deviceownership.exceptionhandler

import com.google.i18n.phonenumbers.NumberParseException
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate

@ControllerAdvice
class ApplicationExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val response = ExceptionResponse(LocalDate.now(), "Validation failed", ex.bindingResult.toString())
        return ResponseEntity.badRequest().body(response)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        return ResponseEntity.badRequest().body(ExceptionResponse(
            date = LocalDate.now(),
            message = "JSON parse error: Cannot deserialize value",
            details = ex.message?: "Http message not readable"
        ))
    }

    @ExceptionHandler(NumberParseException::class)
    fun handlePhoneNumberParseException(ex: NumberParseException, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(LocalDate.now(), ex.message?: "Not a valid phone number", request.getDescription(false))
        return ResponseEntity.badRequest().body(response)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(LocalDate.now(), ex.message?: "Entity not found", request.getDescription(false))
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllOtherException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val response = ExceptionResponse(LocalDate.now(), ex.message?: "Internal server error", request.getDescription(false))
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}