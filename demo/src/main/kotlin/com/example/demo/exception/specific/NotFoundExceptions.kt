package com.example.demo.exception.specific

import com.example.demo.exception.base.NotFoundException

class BankLoanTypeNotFound(id: Long): NotFoundException(
    ErrorCode.BANK_LOAN_DOES_NOT_EXIST_ERROR,
    "Bank loan type with id[$id] doesn't exists!"
)