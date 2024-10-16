package com.example.demo.mapper.loanrequest.request

import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.model.enums.LoanRequestStatus
import com.example.demo.model.loanrequest.LoanRequest
import org.springframework.stereotype.Component

@Component
class CreateLoanRequestMapper {
    fun toEntity(loanRequestDTO: CreateLoanRequestDTO): LoanRequest{
        return with(loanRequestDTO){
            LoanRequest(
                id,
                clientFirstName,
                clientLastName,
                amount,
                LoanRequestStatus.PROCESSING
            )
        }
    }
}