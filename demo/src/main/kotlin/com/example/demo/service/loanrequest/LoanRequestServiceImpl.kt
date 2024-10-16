package com.example.demo.service.loanrequest

import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dao.loanrequest.LoanRequestDao
import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.CreateLoanRequestResponseDTO
import com.example.demo.mapper.loanrequest.request.CreateLoanRequestMapper
import com.example.demo.mapper.loanrequest.response.LoanRequestResponseMapper
import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.model.loanrequeststep.LoanRequestStep
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val loanRequestDao: LoanRequestDao,
    private val createLoanRequestMapper: CreateLoanRequestMapper,
    private val loanRequestResponseMapper: LoanRequestResponseMapper,
    private val bankLoanTypeDao: BankLoanTypeDao
) : LoanRequestService {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun create(loanRequestDTO: CreateLoanRequestDTO): CreateLoanRequestResponseDTO {
        //check if dto.bankloanid exists if not throw exception, if yes set bankloan in loanrequest i steps od tog bank loana namapiraj na loanrequeststeps i onda te loanrequeststeps dodas na loanrequest
        return loanRequestDTO
            .let(createLoanRequestMapper::toEntity)
            .also {
                it.bankLoanType = bankLoanTypeDao.findById(loanRequestDTO.bankLoanTypeId!!)
                it.steps = it.bankLoanType!!.steps.map { s -> LoanRequestStep(
                    s.id,
                    it,
                    s,
                    0,
                    LoanRequestStepStatus.PENDING,
                ) }.toSet()
            } //todo check this
            .let(loanRequestDao::create)
            .let(loanRequestResponseMapper::toDto)
            .also {
                log.info("Loan request with ID ${it.id} has been created")
            }
    }
}