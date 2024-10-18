package com.example.demo.model.loanrequest

import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.model.step.Step
import jakarta.persistence.*

@Entity
@Table(name = "loan_request_steps")
class LoanRequestStep (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_request_id")
    var loanRequest: LoanRequest,
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id")
    var step: Step,
    var spentDurationDay: Int,
    @Enumerated(EnumType.STRING)
    var status: LoanRequestStepStatus
)