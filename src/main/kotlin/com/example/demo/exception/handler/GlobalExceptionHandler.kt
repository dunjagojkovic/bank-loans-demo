package com.example.demo.exception.handler

import com.example.demo.dto.error.ErrorDTO
import com.example.demo.exception.base.HttpStatusException
import com.example.demo.exception.specific.ErrorCode
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(HttpStatusException::class)
    fun handleHttpStatusException(httpStatusException: HttpStatusException): ResponseEntity<ErrorDTO> {
        val errorDTO = ErrorDTO(httpStatusException.message, httpStatusException.code)
            .also(this::logError)
        return ResponseEntity<ErrorDTO>(errorDTO, httpStatusException.status)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(genericException: Exception): ErrorDTO {
        val errorDTO = ErrorDTO(genericException.message, ErrorCode.UNKNOWN_ERROR)
            .also(this::logError)
        return errorDTO
    }

    private fun logError(errorDTO: ErrorDTO) {
        log.info(errorDTO.message)
    }
}