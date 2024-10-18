package com.example.demo.repository.loanrequest

import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.loanrequest.LoanRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LoanRequestRepository: JpaRepository<LoanRequest, Long> {
    @Query("SELECT lr FROM LoanRequest lr WHERE UPPER(lr.status) LIKE CONCAT('%', UPPER(:status), '%')")
    fun findByStatusContainingIgnoreCase(@Param("status") status: String): List<LoanRequest> //todo enum umesto string pokusaj
    fun existsByBankLoanType(bankLoanType: BankLoanType): Boolean
}