package com.example.demo.mapper.bankloantype.response

import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.mapper.step.response.StepResponseMapper
import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.stereotype.Component

@Component
class BankLoanTypeResponseMapper(
    private val stepResponseMapper: StepResponseMapper
) {
    fun toDto(bankLoanType: BankLoanType): BankLoanTypeRequestDTO {
        return with(bankLoanType) {
            BankLoanTypeRequestDTO(
                name,
                steps.map { step -> stepResponseMapper.toDto(step) }.toMutableSet(),
                id
            )
        }
    }
}