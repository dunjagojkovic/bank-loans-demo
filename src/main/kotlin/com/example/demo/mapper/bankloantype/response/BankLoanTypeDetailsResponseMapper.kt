package com.example.demo.mapper.bankloantype.response

import com.example.demo.dto.bankloantype.BankLoanTypeDetailsDTO
import com.example.demo.mapper.step.response.StepResponseMapper
import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.stereotype.Component

@Component
class BankLoanTypeDetailsResponseMapper(
    private val stepResponseMapper: StepResponseMapper
) {
    fun toDto(bankLoanType: BankLoanType): BankLoanTypeDetailsDTO{
        var totalExpectedDays = 0
        bankLoanType.steps.forEach { step -> totalExpectedDays += step.expectedDurationDay }

        return with(bankLoanType) {
            BankLoanTypeDetailsDTO(
                totalExpectedDays,
                steps.map { step -> stepResponseMapper.toDto(step) }.toMutableSet(),
                id
                )
        }
    }
}