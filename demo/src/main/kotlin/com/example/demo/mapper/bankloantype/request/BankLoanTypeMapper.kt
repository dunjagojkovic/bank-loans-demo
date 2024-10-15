package com.example.demo.mapper.bankloantype.request

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.mapper.step.request.StepMapper
import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.stereotype.Component

@Component
class BankLoanTypeMapper(
    private val stepMapper: StepMapper
) {
    fun toEntity(bankLoanTypeDTO: BankLoanTypeDTO): BankLoanType{
        return with(bankLoanTypeDTO){
            BankLoanType(
                id,
                name
            ).also {
                it.steps = bankLoanTypeDTO.steps.map { stepDTO -> stepMapper.toEntity(stepDTO, it) }.toMutableSet() }
        }
    }
}