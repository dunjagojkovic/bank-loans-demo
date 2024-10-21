package com.example.demo.dto.bankloantype

import com.example.demo.dto.step.StepDTO
import com.fasterxml.jackson.annotation.JsonIgnore

data class BankLoanTypeDetailsDTO(
    var totalExpectedDays: Int,
    var name: String,
    var steps: MutableSet<StepDTO> = mutableSetOf(),
    @JsonIgnore val id: Long? = null
)
