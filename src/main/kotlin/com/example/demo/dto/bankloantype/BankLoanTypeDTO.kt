package com.example.demo.dto.bankloantype

import com.example.demo.dto.step.StepDTO
import com.fasterxml.jackson.annotation.JsonIgnore

data class BankLoanTypeDTO(
    var name: String,
    var steps: MutableSet<StepDTO> = mutableSetOf(),
    @JsonIgnore var id: Long? = null
)
