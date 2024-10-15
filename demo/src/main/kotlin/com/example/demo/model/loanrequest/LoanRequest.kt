package com.example.demo.model.loanrequest

import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.enums.LoanRequestStatus
import com.example.demo.model.loanrequeststep.LoanRequestStep
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "loan_requests")
class LoanRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "bank_loan_type", nullable = false)
    val bankLoanType: BankLoanType,
    val clientFirstName: String,
    val clientLastName: String,
    val amount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var status: LoanRequestStatus,
    @OneToMany(mappedBy = "loanRequest", cascade = [CascadeType.ALL], orphanRemoval = true)
    val steps:  Set<LoanRequestStep> = setOf()
) {
}