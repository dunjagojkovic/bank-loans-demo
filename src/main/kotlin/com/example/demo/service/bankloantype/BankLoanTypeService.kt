package com.example.demo.service.bankloantype

import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.dto.bankloantype.response.BankLoanTypeDetailsResponseDTO

interface BankLoanTypeService {
    fun create(bankLoanType: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO
    fun findById(id: Long): BankLoanTypeDetailsResponseDTO
    fun delete(id: Long)
    fun findByName(name: String): List<BankLoanTypeRequestDTO>
    fun update(bankLoanTypeRequestDTO: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO
}