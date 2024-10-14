package com.example.demo.model.bankloantype

import com.example.demo.model.step.Step
import jakarta.persistence.*

@Entity
@Table(name = "bank_loans", uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
class BankLoanType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,  //id pre cuvanja samog bank loan-a u bazi moze biti null jer ga baza automatski generise
    var name: String,
) {
    @OneToMany(mappedBy = "bankLoanType", cascade = [CascadeType.ALL], orphanRemoval = true)
    var steps: MutableSet<Step> = mutableSetOf()
}