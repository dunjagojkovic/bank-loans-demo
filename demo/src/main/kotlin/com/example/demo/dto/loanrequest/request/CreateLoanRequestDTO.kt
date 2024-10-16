package com.example.demo.dto.loanrequest.request

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

data class CreateLoanRequestDTO(
    @JsonIgnore var bankLoanTypeId: Long? = null,
    val clientFirstName: String,
    val clientLastName: String,
    val amount: BigDecimal,
    @JsonIgnore var id: Long? = null
)
