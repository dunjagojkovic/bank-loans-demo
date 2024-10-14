package com.example.demo.dao.bankloantype

import com.example.demo.exception.specific.BankLoanTypeNameAlreadyExists
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.repository.bankloantype.BankLoanTypeRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class BankLoanTypeDaoImpl(
    private val bankLoanTypeRepository: BankLoanTypeRepository
): BankLoanTypeDao {
    override fun create(bankLoanType: BankLoanType): BankLoanType {
        bankLoanTypeRepository.findByName(bankLoanType.name).getOrNull()
            ?.let {
                throw BankLoanTypeNameAlreadyExists(bankLoanType.name)
            }
       return bankLoanTypeRepository.save(bankLoanType)
    }

}