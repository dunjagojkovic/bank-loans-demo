package com.example.demo.model.loanrequest

import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.enums.LoanRequestStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "loan_requests")
class LoanRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val clientFirstName: String,
    val clientLastName: String,
    val amount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var status: LoanRequestStatus,
) {

    @PrePersist
    fun prePersist(){
        steps.forEach{ it.loanRequest = this}
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_loan_type", nullable = false)
    var bankLoanType: BankLoanType? = null
    @OneToMany(mappedBy = "loanRequest", cascade = [CascadeType.ALL], orphanRemoval = true)
    var steps:  Set<LoanRequestStep> = setOf()
}