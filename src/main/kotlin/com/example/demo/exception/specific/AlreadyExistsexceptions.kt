package com.example.demo.exception.specific

import com.example.demo.exception.base.AlreadyExistsException

class BankLoanTypeNameAlreadyExists(name: String): AlreadyExistsException(
    ErrorCode.BANK_LOAN_TYPE_NAME_ALREADY_EXISTS_ERROR,
    "Bank loan type with name[$name] already exists!"
)