package com.example.demo.dto.error

import org.springframework.http.HttpStatus

data class ErrorResponseDTO(
    val message: String?,
    val code: String
)