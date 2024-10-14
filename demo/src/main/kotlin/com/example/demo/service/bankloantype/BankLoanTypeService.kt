package com.example.demo.service.bankloantype

import com.example.demo.dto.bankloantype.BankLoanTypeDTO

interface BankLoanTypeService {
    fun create(bankLoanType: BankLoanTypeDTO): BankLoanTypeDTO
}