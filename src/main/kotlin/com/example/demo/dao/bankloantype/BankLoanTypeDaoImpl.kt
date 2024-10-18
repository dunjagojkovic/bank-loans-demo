package com.example.demo.dao.bankloantype

import com.example.demo.exception.specific.BankLoanTypeNameAlreadyExists
import com.example.demo.exception.specific.BankLoanTypeNotFound
import com.example.demo.exception.specific.LoanRequestForBankLoanTypeExists
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.repository.bankloantype.BankLoanTypeRepository
import com.example.demo.repository.loanrequest.LoanRequestRepository
import org.springframework.stereotype.Service

@Service
class BankLoanTypeDaoImpl(
    private val bankLoanTypeRepository: BankLoanTypeRepository,
    private val loanRequestRepository: LoanRequestRepository
): BankLoanTypeDao {
    override fun create(bankLoanType: BankLoanType): BankLoanType {
        return bankLoanType
            .also(this::validateBankLoanTypeName)
            .let(bankLoanTypeRepository::save)
    }

    override fun findById(id: Long): BankLoanType {
        return id
            .let(bankLoanTypeRepository::findById)
            .orElseThrow{ BankLoanTypeNotFound(id) }
    }

    override fun delete(id: Long) {
        id.also(this::validateBankLoanTypeDelete).run(bankLoanTypeRepository::deleteById)
    }

    override fun findByName(name: String): List<BankLoanType> {
        return name
            .let(bankLoanTypeRepository::findByNameContainingIgnoreCase)
    }

    override fun update(bankLoanType: BankLoanType): BankLoanType {
        return bankLoanType
            .also(this::validateBankLoanTypeUpdate)
            .let(bankLoanTypeRepository::save)
    }

    private fun validateBankLoanTypeName(bankLoanType: BankLoanType) {
        bankLoanType.name
            .let(bankLoanTypeRepository::findByName)
            .ifPresent{ throw BankLoanTypeNameAlreadyExists(it.name) }
    }

    //todo refactor
    private fun checkExistingLoanRequests(bankLoanType: BankLoanType) {
        if(loanRequestRepository.existsByBankLoanType(bankLoanType)){
            throw LoanRequestForBankLoanTypeExists(bankLoanType.id!!)
        }
    }
    //todo refactor
    private fun validateBankLoanTypeUpdate(bankLoanType: BankLoanType) {
        bankLoanType.id?.let(this::findById)
             .also{this.checkExistingLoanRequests(it!!)}
        validateBankLoanTypeName(bankLoanType)
    }

    private fun validateBankLoanTypeDelete(bankLoanTypeId: Long) {
       this.findById(bankLoanTypeId).also { this.checkExistingLoanRequests(it) }
    }
}