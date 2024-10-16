package com.example.demo.dao.loanrequest

import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.model.loanrequest.LoanRequest
import com.example.demo.repository.loanrequest.LoanRequestRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestDaoImpl(
    private val loanRequestRepository: LoanRequestRepository,
    private val bankLoanTypeDao: BankLoanTypeDao
): LoanRequestDao {
    override fun create(loanRequest: LoanRequest): LoanRequest {
        return loanRequest
            //.also{ bankLoanTypeDao.findById(loanRequest.bankLoanType!!.id!!)}
            .let(loanRequestRepository::save)
    }
}