package com.example.demo.mapper.step.request

import com.example.demo.dto.step.StepDTO
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.step.Step
import org.springframework.stereotype.Component

@Component
class StepMapper {
    fun toEntity(stepDTO: StepDTO, bankLoanType: BankLoanType): Step{
        return with(stepDTO) {
            Step(
                id,
                name,
                orderNumber,
                expectedDurationDay,
                bankLoanType
            )
        }
    }
}