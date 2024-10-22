package com.example.demo.dto.bankloantype.request

import com.example.demo.dto.step.StepDTO
import com.fasterxml.jackson.annotation.JsonIgnore

data class BankLoanTypeRequestDTO(
    var name: String,
    var steps: MutableSet<StepDTO> = mutableSetOf(),
    @JsonIgnore var id: Long? = null
)
