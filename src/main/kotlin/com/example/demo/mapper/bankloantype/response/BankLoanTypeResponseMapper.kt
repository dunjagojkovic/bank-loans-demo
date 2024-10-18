package com.example.demo.mapper.bankloantype.response

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.mapper.step.response.StepResponseMapper
import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.stereotype.Component

@Component
class BankLoanTypeResponseMapper(
    private val stepResponseMapper: StepResponseMapper
) {
    fun toDto(bankLoanType: BankLoanType): BankLoanTypeDTO {
        return with(bankLoanType) {
            BankLoanTypeDTO(
                name,
                steps.map { step -> stepResponseMapper.toDto(step) }.toMutableSet(),
                id
            )
        }
    }
}