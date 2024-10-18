package com.example.demo.service.loanrequest.loanrequeststep

import com.example.demo.dao.loanrequest.LoanRequestDao
import com.example.demo.dto.loanrequest.request.UpdateLoanRequestStepStatusDTO
import com.example.demo.exception.specific.LoanRequestStepStatusChange
import com.example.demo.exception.specific.PreviousStepsNotSuccessful
import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.service.loanrequest.LoanRequestService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LoanRequestStepServiceImpl(
    private val loanRequestDao: LoanRequestDao,
    private val loanRequestService: LoanRequestService
): LoanRequestStepService {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)
//todo refactor!!!!!!!!
    override fun updateStatus(loanRequestId: Long, stepId: Long, newInfo: UpdateLoanRequestStepStatusDTO) {
        val loanRequest = loanRequestDao.findById(loanRequestId)
        val stepToUpdate = loanRequest.steps.first { loanRequestStep -> loanRequestStep.id!! == stepId } //todo or else throw exception

        if(stepToUpdate.status != LoanRequestStepStatus.PENDING){
            throw LoanRequestStepStatusChange(stepId)
        }

        val anyPreviousStepNotCompleted = loanRequest.steps.any { loanRequestStep ->
            loanRequestStep.step.orderNumber < stepToUpdate.step.orderNumber
                    && loanRequestStep.status != LoanRequestStepStatus.SUCCESSFUL
        }

        if(anyPreviousStepNotCompleted){
            throw PreviousStepsNotSuccessful(loanRequestId, stepId)
        }

        stepToUpdate.status = newInfo.newStatus
        stepToUpdate.spentDurationDay = newInfo.durationDay
        loanRequestService.updateStatus(loanRequest)
    }

}