package com.example.demo.repository.bankloantype

import com.example.demo.model.bankloantype.BankLoanType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BankLoanTypeRepository : JpaRepository<BankLoanType, Long>{
    fun findByName(name: String): Optional<BankLoanType>
}