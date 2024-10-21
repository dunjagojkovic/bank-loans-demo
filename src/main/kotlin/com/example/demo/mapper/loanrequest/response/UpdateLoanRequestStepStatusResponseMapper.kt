package com.example.demo.mapper.loanrequest.response

import com.example.demo.dto.loanrequest.request.LoanRequestStepDTO
import com.example.demo.dto.loanrequest.response.UpdateLoanRequestStepStatusResponseDTO
import com.example.demo.model.loanrequest.LoanRequest
import org.springframework.stereotype.Component

@Component
class UpdateLoanRequestStepStatusResponseMapper {

    fun toDto(loanRequest: LoanRequest): UpdateLoanRequestStepStatusResponseDTO {
        return with(loanRequest){
            var totalExpectedDurationDay = 0

            loanRequest.bankLoanType!!.steps.forEach{step -> totalExpectedDurationDay += step.expectedDurationDay }

            UpdateLoanRequestStepStatusResponseDTO(
                bankLoanType!!.name,
                totalExpectedDurationDay,
                clientFirstName,
                clientLastName,
                amount,
                status,
                steps.map { loanRequestStep -> //todo extract to new fun
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