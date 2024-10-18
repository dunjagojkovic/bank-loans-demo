package com.example.demo.service.loanrequest.loanrequeststep

import com.example.demo.dao.loanrequest.LoanRequestDao
import com.example.demo.dto.loanrequest.request.UpdateLoanRequestStepStatusDTO
import com.example.demo.dto.loanrequest.response.LoanRequestResponseDTO
import com.example.demo.dto.loanrequest.response.UpdateLoanRequestStepStatusResponseDTO
import com.example.demo.exception.specific.LoanRequestStepNotFound
import com.example.demo.exception.specific.LoanRequestStepStatusChange
import com.example.demo.exception.specific.PreviousStepsNotSuccessful
import com.example.demo.model.enums.LoanRequestStepStatus
import com.example.demo.model.loanrequest.LoanRequestStep
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

    override fun updateStatus(loanRequestId: Long, stepId: Long, newInfo: UpdateLoanRequestStepStatusDTO): UpdateLoanRequestStepStatusResponseDTO {
        return loanRequestId
            .let(loanRequestDao::findById).steps.find { it.id!! == stepId }
            .also { validate(stepId, it) }!!
            .apply {
                status = newInfo.newStatus
                spentDurationDay = newInfo.durationDay
        }.loanRequest
            .let(loanRequestService::updateStatus)
    }

    private fun validate(stepId: Long, stepToUpdate: LoanRequestStep?) {
        if (stepToUpdate == null) {
            throw LoanRequestStepNotFound(stepId, 0 ) //todo check this loan request id
        }

        if (stepToUpdate.status != LoanRequestStepStatus.PENDING) {
            throw LoanRequestStepStatusChange(stepId)
        }
        //todo check this logic
        val anyPreviousStepNotCompleted = stepToUpdate.loanRequest.steps.any {
            it.step.orderNumber < stepToUpdate.step.orderNumber
                    && it.status != LoanRequestStepStatus.SUCCESSFUL
        }

        if(anyPreviousStepNotCompleted){
            throw PreviousStepsNotSuccessful(stepToUpdate.loanRequest.id!!, stepId)
        }
    }

    private fun validatePreviousSteps(it: LoanRequestStep, stepToUpdate: LoanRequestStep) =
        (it.step.orderNumber < stepToUpdate.step.orderNumber && it.status != LoanRequestStepStatus.SUCCESSFUL)

}