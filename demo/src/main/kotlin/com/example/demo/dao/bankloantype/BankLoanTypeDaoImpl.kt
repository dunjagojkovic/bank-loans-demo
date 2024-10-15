package com.example.demo.dao.bankloantype

import com.example.demo.exception.specific.BankLoanTypeNameAlreadyExists
import com.example.demo.exception.specific.BankLoanTypeNotFound
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.repository.bankloantype.BankLoanTypeRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class BankLoanTypeDaoImpl(
    private val bankLoanTypeRepository: BankLoanTypeRepository
): BankLoanTypeDao {
    override fun create(bankLoanType: BankLoanType): BankLoanType { //todo: pitaj da li treba uopste poziv ka bazi s obzirom da je unique constraint def na nivou tabele za name pa je suvisan poziv ka bazi? kako unaprediti ovaj kod
        bankLoanTypeRepository.findByName(bankLoanType.name).getOrNull()
            ?.let {
                throw BankLoanTypeNameAlreadyExists(bankLoanType.name)
            }
       return bankLoanTypeRepository.save(bankLoanType)
    }

    override fun findById(id: Long): BankLoanType {
        return bankLoanTypeRepository.findById(id)
            .orElseThrow { BankLoanTypeNotFound(id) }
    }

    override fun delete(id: Long) {
        return id.also(this::findById).run(bankLoanTypeRepository::deleteById)
    }

    override fun findByName(name: String): List<BankLoanType> {
        return bankLoanTypeRepository.findByNameContainingIgnoreCase(name)
    }

    override fun update(bankLoanType: BankLoanType): BankLoanType {
        return this.findById(bankLoanType.id!!)
            .also {
                bankLoanTypeRepository.findByName(bankLoanType.name).getOrNull()
                    ?.let {
                        throw BankLoanTypeNameAlreadyExists(bankLoanType.name)
                    }
            }
            .run { bankLoanTypeRepository.save(bankLoanType) }
    }
}