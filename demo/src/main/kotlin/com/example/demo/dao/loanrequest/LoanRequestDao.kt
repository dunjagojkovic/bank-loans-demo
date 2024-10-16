package com.example.demo.dao.loanrequest

import com.example.demo.model.loanrequest.LoanRequest

interface LoanRequestDao {
    fun create(loanRequest: LoanRequest): LoanRequest
}