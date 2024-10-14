package com.example.demo.exception.base

import org.springframework.http.HttpStatus

open class AlreadyExistsException(
    override val code: String,
    override val message: String
): HttpStatusException(HttpStatus.BAD_REQUEST, code, message)