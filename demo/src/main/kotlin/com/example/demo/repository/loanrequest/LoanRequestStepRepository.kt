package com.example.demo.repository.loanrequest

import com.example.demo.model.loanrequest.LoanRequestStep
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRequestStepRepository : JpaRepository<LoanRequestStep, Long> {
}