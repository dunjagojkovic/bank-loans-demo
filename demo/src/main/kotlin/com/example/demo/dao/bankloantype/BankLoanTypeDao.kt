package com.example.demo.dao.bankloantype

import com.example.demo.model.bankloantype.BankLoanType

interface BankLoanTypeDao {
    fun create(bankLoanType: BankLoanType): BankLoanType
    fun findById(id: Long) : BankLoanType
}