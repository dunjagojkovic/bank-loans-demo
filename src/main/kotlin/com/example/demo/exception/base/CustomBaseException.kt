package com.example.demo.exception.base

import java.time.LocalDateTime

open class CustomBaseException(override val message: String): RuntimeException(message){
    val timeStamp: LocalDateTime = LocalDateTime.now()
}