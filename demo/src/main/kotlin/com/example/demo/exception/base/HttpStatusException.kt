package com.example.demo.exception.base

import org.springframework.http.HttpStatus

open class HttpStatusException(
    val status: HttpStatus,
    open val code: String,
    override val message: String
) : CustomBaseException(message)