package com.example.demo.model.loanrequeststep

import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.model.loanrequest.LoanRequest
import com.example.demo.model.step.Step
import jakarta.persistence.*

@Entity
@Table(name = "loan_request_steps")
class LoanRequestStep (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(optional = false)
    @JoinColumn(name = "loan_request_id")
    val loanRequest: LoanRequest,
    @ManyToOne(optional = false)
    @JoinColumn(name = "step_id")
    val step: Step,
    val spentDurationDay: Int,
    @Enumerated(EnumType.STRING)
    var status: LoanRequestStepStatus
)