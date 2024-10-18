package com.example.demo.dao.loanrequest

import com.example.demo.exception.specific.LoanRequestNotFound
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.loanrequest.LoanRequest
import com.example.demo.repository.loanrequest.LoanRequestRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestDaoImpl(
    private val loanRequestRepository: LoanRequestRepository
): LoanRequestDao {

    override fun create(loanRequest: LoanRequest): LoanRequest {
        return loanRequest
            .let(loanRequestRepository::save)
    }

    override fun findByStatus(status: String): List<LoanRequest> {
        return status
            .let(loanRequestRepository::findByStatusContainingIgnoreCase)
    }

    override fun existsByBankLoanType(bankLoanType: BankLoanType): Boolean {
        return bankLoanType
            .let(loanRequestRepository::existsByBankLoanType)
    }

    override fun updateStatus(loanRequest: LoanRequest): LoanRequest {
        return loanRequest
            .let(loanRequestRepository::save)
    }

    override fun findById(id: Long): LoanRequest {
        return id
            .let(loanRequestRepository::findById)
            .orElseThrow{ LoanRequestNotFound(id) }
    }
}