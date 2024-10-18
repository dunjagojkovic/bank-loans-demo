package com.example.demo.service.loanrequest.loanrequeststep

import com.example.demo.dto.loanrequest.request.UpdateLoanRequestStepStatusDTO
import com.example.demo.dto.loanrequest.response.UpdateLoanRequestStepStatusResponseDTO

interface LoanRequestStepService {
    fun updateStatus(loanRequestId: Long, stepId: Long, newInfo: UpdateLoanRequestStepStatusDTO): UpdateLoanRequestStepStatusResponseDTO
}