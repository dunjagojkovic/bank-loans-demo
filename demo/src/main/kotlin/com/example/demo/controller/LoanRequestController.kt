package com.example.demo.controller

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.service.loanrequest.LoanRequestService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loan-requests")
class LoanRequestController(
    private val loanRequestService: LoanRequestService
) {
    //todo pitaj ovaj endpoint moze ovako da ostane jer search nije na nivou odredjenog bank loan type vec general no za sve loan requestove
    @PostMapping("/bank-loan-id/{bankLoanTypeId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@PathVariable bankLoanTypeId: Long, @RequestBody loanRequestDTO: CreateLoanRequestDTO): LoanRequestResponseDTO {
        return loanRequestDTO
            .apply { this.bankLoanTypeId = bankLoanTypeId }
            .let(loanRequestService::create)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchByStatus(@RequestParam status: String): List<LoanRequestResponseDTO> {
        return loanRequestService.findByStatus(status)
    }
}