package com.example.demo.dto.bankloantype.response

import com.example.demo.dto.step.StepDTO

data class BankLoanTypeDetailsResponseDTO(
    var totalExpectedDays: Int,
    var name: String,
    var steps: MutableSet<StepDTO> = mutableSetOf(),
    val id: Long? = null
)
