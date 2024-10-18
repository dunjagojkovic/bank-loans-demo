package com.example.demo.mapper.loanrequest.response

import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.dto.loanrequest.request.LoanRequestStepDTO
import com.example.demo.model.enums.LoanRequestStatus
import com.example.demo.model.loanrequest.LoanRequest
import org.springframework.stereotype.Component

@Component
class CreateLoanRequestResponseMapper {
    fun toDto(loanRequest: LoanRequest): LoanRequestResponseDTO{
        return with(loanRequest){
            var totalExpectedDurationDay = 0

            loanRequest.bankLoanType!!.steps.forEach{step -> totalExpectedDurationDay += step.expectedDurationDay }

            LoanRequestResponseDTO(
                bankLoanType!!.name,
                totalExpectedDurationDay,
                clientFirstName,
                clientLastName,
                amount,
                LoanRequestStatus.PROCESSING,
                steps.map { loanRequestStep ->
                    LoanRequestStepDTO(
                        loanRequestStep.step.name,
                        loanRequestStep.step.orderNumber,
                        loanRequestStep.step.expectedDurationDay,
                        loanRequestStep.spentDurationDay,
                        loanRequestStep.status,
                        loanRequestStep.step.id,
                        id
                    )
                }.toSet(),
                id
            )
        }
    }
}