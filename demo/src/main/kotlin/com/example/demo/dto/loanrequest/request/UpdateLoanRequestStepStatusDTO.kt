package com.example.demo.dto.loanrequest.request

import com.example.demo.model.enums.LoanRequestStepStatus

data class UpdateLoanRequestStepStatusDTO(
    var newStatus: LoanRequestStepStatus,
    var durationDay: Int
)
