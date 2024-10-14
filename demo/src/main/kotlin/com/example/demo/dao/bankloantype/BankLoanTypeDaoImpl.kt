package com.example.demo.dao.bankloantype

import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.repository.bankloantype.BankLoanTypeRepository
import org.springframework.stereotype.Service

@Service
class BankLoanTypeDaoImpl(
    private val bankLoanTypeRepository: BankLoanTypeRepository
): BankLoanTypeDao {
    override fun create(bankLoanType: BankLoanType): BankLoanType {
       return bankLoanTypeRepository.save(bankLoanType) //todo custom exception name must be unique
    }

}