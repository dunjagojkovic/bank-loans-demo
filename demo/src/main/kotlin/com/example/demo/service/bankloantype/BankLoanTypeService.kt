package com.example.demo.service.bankloantype

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.dto.bankloantype.BankLoanTypeDetailsDTO
import com.example.demo.model.bankloantype.BankLoanType

interface BankLoanTypeService {
    fun create(bankLoanType: BankLoanTypeDTO): BankLoanTypeDTO
    fun findById(id: Long): BankLoanTypeDetailsDTO
    fun delete(id: Long)
    fun findByName(name: String): List<BankLoanTypeDTO>
}