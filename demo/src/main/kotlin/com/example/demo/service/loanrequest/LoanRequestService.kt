package com.example.demo.service.loanrequest

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.CreateLoanRequestResponseDTO

interface LoanRequestService {
    fun create(loanRequestDTO: CreateLoanRequestDTO): CreateLoanRequestResponseDTO
}