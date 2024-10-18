package com.example.demo.dto.step

import com.fasterxml.jackson.annotation.JsonIgnore

data class StepDTO(
    var name: String,
    var orderNumber: Int,
    var expectedDurationDay: Int,
    @JsonIgnore val id: Long? = null
)
