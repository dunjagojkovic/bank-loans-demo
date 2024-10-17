package com.example.demo.controller

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.CreateLoanRequestResponseDTO
import com.example.demo.service.loanrequest.LoanRequestService
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/loan-requests")
class LoanRequestController(
    private val loanRequestService: LoanRequestService
) {
    @PostMapping("/bank-loan-id/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable id: Long, @RequestBody loanRequestDTO: CreateLoanRequestDTO): CreateLoanRequestResponseDTO {
        return loanRequestDTO
            .apply { this.bankLoanTypeId = id }
            .let(loanRequestService::create)
    }
}