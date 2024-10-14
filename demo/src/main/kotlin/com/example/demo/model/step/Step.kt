package com.example.demo.model.step

import com.example.demo.model.bankloantype.BankLoanType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(name = "defined_loan_steps")
class Step(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,
    var name: String,
    var orderNumber: Int,
    var expectedDurationDay: Int,
    @ManyToOne(optional = false) //null je za kotlin a ne za bazu, mozda prepersis bude bio potreban
    @JoinColumn(name = "bank_loan_type_id")
    var bankLoanType: BankLoanType
){

}
