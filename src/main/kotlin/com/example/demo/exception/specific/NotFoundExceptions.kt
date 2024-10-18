package com.example.demo.exception.specific

import com.example.demo.exception.base.NotFoundException

class BankLoanTypeNotFound(id: Long): NotFoundException(
    ErrorCode.BANK_LOAN_TYPE_DOES_NOT_EXIST_ERROR,
    "Bank loan type with id[$id] doesn't exists!"
)

class LoanRequestNotFound(id: Long): NotFoundException(
    ErrorCode.LOAN_REQUEST_DOES_NOT_EXISTS_ERROR,
    "Loan request with id[$id] doesn't exists!"
)

class LoanRequestStepNotFound(id: Long, loanRequestId: Long): NotFoundException(
    ErrorCode.LOAN_REQUEST_STEP_DOES_NOT_EXISTS_ERROR,
    "Loan request step with id[$id] doesn't exists for loan request with id[$loanRequestId]!"
)