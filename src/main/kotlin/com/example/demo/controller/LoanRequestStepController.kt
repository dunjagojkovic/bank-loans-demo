package com.example.demo.controller

import com.example.demo.dto.loanrequest.request.UpdateLoanRequestStepStatusDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.dto.loanrequest.response.UpdateLoanRequestStepStatusResponseDTO
import com.example.demo.service.loanrequest.loanrequeststep.LoanRequestStepService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loan-requests/{loanRequestId}/steps")
class LoanRequestStepController(
    private val loanRequestStepService: LoanRequestStepService
) {
    @PatchMapping("/{stepId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(@PathVariable loanRequestId: Long, @PathVariable stepId: Long, @RequestBody newInfo: UpdateLoanRequestStepStatusDTO): UpdateLoanRequestStepStatusResponseDTO {
        return loanRequestStepService.updateStatus(loanRequestId, stepId, newInfo)
    }
}