package com.example.demo.model.bankloantype

import com.example.demo.model.step.Step
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "bank_loans")
class BankLoanType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(unique = true, nullable = false)
    val name: String
) {
    @OneToMany(mappedBy = "bankLoanType", cascade = [CascadeType.ALL], orphanRemoval = true)
    val steps: MutableSet<Step> = mutableSetOf()
}