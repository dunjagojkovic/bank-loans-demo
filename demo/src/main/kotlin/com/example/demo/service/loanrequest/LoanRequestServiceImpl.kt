package com.example.demo.service.loanrequest

import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dao.loanrequest.LoanRequestDao
import com.example.demo.dto.loanrequest.request.CreateLoanRequestDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.mapper.loanrequest.request.CreateLoanRequestMapper
import com.example.demo.mapper.loanrequest.response.LoanRequestResponseMapper
import com.example.demo.model.enums.LoanRequestStatus
import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.model.loanrequest.LoanRequest
import com.example.demo.model.loanrequest.LoanRequestStep
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoanRequestServiceImpl(
    private val loanRequestDao: LoanRequestDao,
    private val createLoanRequestMapper: CreateLoanRequestMapper,
    private val loanRequestResponseMapper: LoanRequestResponseMapper,
    private val bankLoanTypeDao: BankLoanTypeDao
) : LoanRequestService {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun create(loanRequestDTO: CreateLoanRequestDTO): LoanRequestResponseDTO {
        return loanRequestDTO
            .let(createLoanRequestMapper::toEntity)
            .also {
                it.bankLoanType = bankLoanTypeDao.findById(loanRequestDTO.bankLoanTypeId!!)
                it.steps = it.bankLoanType!!.steps.map { s -> LoanRequestStep(
                    null, //todo change to id??? zasto nece
                    it,
                    s,
                    0,
                    LoanRequestStepStatus.PENDING,
                ) }.toSet()
            }
            .let(loanRequestDao::create)
            .let(loanRequestResponseMapper::toDto)
            .also {
                log.info("Loan request with ID ${it.id} has been created")
            }
    }

    override fun findByStatus(status: String): List<LoanRequestResponseDTO> {
        return status
            .let(loanRequestDao::findByStatus)
            .map(loanRequestResponseMapper::toDto)
    }
    //todo refactor!!!!!!!
    override fun updateStatus(loanRequest: LoanRequest) {
        val allStepsSuccessful = loanRequest.steps.all {
            loanRequestStep -> loanRequestStep.status == LoanRequestStepStatus.SUCCESSFUL
        }

        val anyStepFailed = loanRequest.steps.any {
            loanRequestStep -> loanRequestStep.status == LoanRequestStepStatus.FAILED
        }

        if(anyStepFailed) {
            loanRequest.status = LoanRequestStatus.REJECTED
        } else if(allStepsSuccessful) {
            loanRequest.status = LoanRequestStatus.APPROVED
        } else {
            loanRequest.status = LoanRequestStatus. PROCESSING
        }
        loanRequestDao.updateStatus(loanRequest)
    }
}