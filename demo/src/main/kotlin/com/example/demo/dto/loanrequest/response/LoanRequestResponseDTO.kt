package com.example.demo.dto.loanrequest.response

import com.example.demo.dto.loanrequest.request.LoanRequestStepDTO
import com.example.demo.model.enums.LoanRequestStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

data class LoanRequestResponseDTO(
    val bankLoanTypeName: String,
    val totalExpectedDurationDay: Int,
    val clientFirstName: String,
    val clientLastName: String,
    val amount: BigDecimal,
    var status: LoanRequestStatus,
    val steps: Set<LoanRequestStepDTO> = setOf(),
    @JsonIgnore val id: Long? = null
)