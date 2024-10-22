package com.example.demo.mapper.bankloantype.request

import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.mapper.step.request.StepMapper
import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.stereotype.Component

@Component
class BankLoanTypeMapper(
    private val stepMapper: StepMapper
) {
    fun toEntity(bankLoanTypeRequestDTO: BankLoanTypeRequestDTO): BankLoanType{
        return with(bankLoanTypeRequestDTO){
            BankLoanType(
                id,
                name
            ).also {
                it.steps = bankLoanTypeRequestDTO.steps.map { stepDTO -> stepMapper.toEntity(stepDTO, it) }.toMutableSet() }
        }
    }
}