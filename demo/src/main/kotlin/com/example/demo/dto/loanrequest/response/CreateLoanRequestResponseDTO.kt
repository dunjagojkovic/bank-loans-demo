package com.example.demo.dto.loanrequest.response

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.dto.loanrequeststep.LoanRequestStepDTO
import com.example.demo.model.enums.LoanRequestStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal

data class CreateLoanRequestResponseDTO(
    val bankLoanTypeDTO: BankLoanTypeDTO, //todo umesto ovoga vrati samo ime od bank loana
    val clientFirstName: String,
    val clientLastName: String,
    val amount: BigDecimal,
    var status: LoanRequestStatus,
    val steps: Set<LoanRequestStepDTO> = setOf(),
    @JsonIgnore val id: Long? = null
)