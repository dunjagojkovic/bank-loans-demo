package com.example.demo.service.loanrequest

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.dto.loanrequest.response.UpdateLoanRequestStepStatusResponseDTO
import com.example.demo.model.loanrequest.LoanRequest

interface LoanRequestService {
    fun create(loanRequestDTO: CreateLoanRequestDTO): LoanRequestResponseDTO
    fun findByStatus(status: String): List<LoanRequestResponseDTO>
    fun updateStatus(loanRequest: LoanRequest): UpdateLoanRequestStepStatusResponseDTO
}