package com.example.demo.exception.specific

import com.example.demo.exception.base.InvalidActionException

class LoanRequestStepStatusChange(loanRequestStepId: Long): InvalidActionException(
    ErrorCode.LOAN_REQUEST_STEP_STATUS_CHANGE_ERROR,
    "Loan request step with id[$loanRequestStepId]is already completed!"
)

class PreviousStepsNotSuccessful(loanRequestId: Long, loanRequestStepId: Long): InvalidActionException(
    ErrorCode.PREVIOUS_STEPS_NOT_SUCCESSFUL_ERROR,
    "All previous steps for loan request with id[$loanRequestId] must be successful before updating step with id[$loanRequestStepId]!"
)

class LoanRequestForBankLoanTypeExists(bankLoanTypeId: Long): InvalidActionException(
    ErrorCode.LOAN_REQUEST_FOR_BANK_LOAN_TYPE_EXISTS_ERROR,
    "Bank loan type with id[$bankLoanTypeId] has loan requests associated with it!  "
)