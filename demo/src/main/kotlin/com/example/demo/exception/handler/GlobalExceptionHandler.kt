package com.example.demo.exception.handler

import com.example.demo.dto.error.ErrorResponseDTO
import com.example.demo.exception.base.HttpStatusException
import com.example.demo.exception.specific.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(HttpStatusException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpStatusException(httpStatusException: HttpStatusException): ErrorResponseDTO{
        val errorResponseDTO = ErrorResponseDTO(httpStatusException.message, httpStatusException.code)
            .also(this::logError)
        return errorResponseDTO
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(genericException: Exception): ErrorResponseDTO {
        val errorResponseDTO = ErrorResponseDTO(genericException.message, ErrorCode.UNKNOWN_ERROR)
            .also(this::logError)
        return errorResponseDTO
    }

    private fun logError(errorResponseDTO: ErrorResponseDTO) {
        log.info(errorResponseDTO.message)
    }
}