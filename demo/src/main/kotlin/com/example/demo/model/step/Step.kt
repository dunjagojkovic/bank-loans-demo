package com.example.demo.model.step

import com.example.demo.model.bankloantype.BankLoanType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "defined_loan_steps")
class Step(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val orderNumber: Int,
    val expectedDurationDay: Int,
    @ManyToOne
    @JoinColumn(name = "bank_loan_type_id", nullable = false)
    val bankLoanType: BankLoanType
)