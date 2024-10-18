package com.example.demo.dto.loanrequest.request

import com.example.demo.model.enums.LoanRequestStepStatus
import com.fasterxml.jackson.annotation.JsonIgnore

data class LoanRequestStepDTO(
    val name: String,
    val orderNumber: Int,
    val expectedDurationDay: Int,
    val spentDurationDay: Int,
    val status: LoanRequestStepStatus,
    @JsonIgnore val stepId: Long? = null,
    @JsonIgnore val id: Long? = null
)