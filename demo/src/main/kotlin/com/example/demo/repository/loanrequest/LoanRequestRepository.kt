package com.example.demo.repository.loanrequest

import com.example.demo.model.loanrequest.LoanRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRequestRepository: JpaRepository<LoanRequest, Long> {
}