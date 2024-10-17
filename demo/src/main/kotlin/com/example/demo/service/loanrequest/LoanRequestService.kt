package com.example.demo.service.loanrequest

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO

interface LoanRequestService {
    fun create(loanRequestDTO: CreateLoanRequestDTO): LoanRequestResponseDTO
    fun findByStatus(status: String): List<LoanRequestResponseDTO>
}