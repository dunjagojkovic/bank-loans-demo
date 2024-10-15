package com.example.demo.exception.base

import org.springframework.http.HttpStatus

open class NotFoundException(
    override val code: String,
    override val message: String
): HttpStatusException(HttpStatus.NOT_FOUND, code, message)


