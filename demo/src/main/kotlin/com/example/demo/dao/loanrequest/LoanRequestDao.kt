package com.example.demo.dao.loanrequest

import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.loanrequest.LoanRequest

interface LoanRequestDao {
    fun create(loanRequest: LoanRequest): LoanRequest
    fun findByStatus(status: String): List<LoanRequest>
    fun existsByBankLoanType(bankLoanType: BankLoanType): Boolean
}