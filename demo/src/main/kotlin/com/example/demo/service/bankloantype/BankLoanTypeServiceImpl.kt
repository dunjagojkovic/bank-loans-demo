package com.example.demo.service.bankloantype

import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.mapper.bankloantype.request.BankLoanTypeMapper
import com.example.demo.mapper.bankloantype.response.BankLoanTypeResponseMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BankLoanTypeServiceImpl(
    private val bankLoanTypeDao: BankLoanTypeDao,
    private val bankLoanMapper: BankLoanTypeMapper,
    private val bankLoanTypeResponseMapper: BankLoanTypeResponseMapper
): BankLoanTypeService {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun create(bankLoanType: BankLoanTypeDTO): BankLoanTypeDTO {
        return bankLoanType
            .let(bankLoanMapper::toEntity)
            .let(bankLoanTypeDao::create)
            .let(bankLoanTypeResponseMapper::toDto)
            .also {
                log.info("Bank loan type with ID ${it.id} has been created")
            }
    }
}