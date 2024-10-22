package com.example.demo.service.bankloantype

import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.dto.bankloantype.response.BankLoanTypeDetailsResponseDTO
import com.example.demo.mapper.bankloantype.request.BankLoanTypeMapper
import com.example.demo.mapper.bankloantype.response.BankLoanTypeDetailsResponseMapper
import com.example.demo.mapper.bankloantype.response.BankLoanTypeResponseMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BankLoanTypeServiceImpl(
    private val bankLoanTypeDao: BankLoanTypeDao,
    private val bankLoanMapper: BankLoanTypeMapper,
    private val bankLoanTypeResponseMapper: BankLoanTypeResponseMapper,
    private val bankLoanTypeDetailsResponseMapper: BankLoanTypeDetailsResponseMapper
): BankLoanTypeService {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun create(bankLoanType: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO {
        return bankLoanType
            .let(bankLoanMapper::toEntity)
            .let(bankLoanTypeDao::create)
            .let(bankLoanTypeResponseMapper::toDto)
            .also {
                log.info("Bank loan type with ID ${it.id} has been created")
            }
    }

    override fun findById(id: Long): BankLoanTypeDetailsResponseDTO {
        return id
            .let(bankLoanTypeDao::findById)
            .let(bankLoanTypeDetailsResponseMapper::toDto)
    }

    override fun delete(id: Long) {
         bankLoanTypeDao.delete(id)
            .also {
                log.info("Bank loan type with ID $id has been deleted")
            }
    }

    override fun findByName(name: String): List<BankLoanTypeRequestDTO> {
        return name
            .let(bankLoanTypeDao::findByName)
            .map(bankLoanTypeResponseMapper::toDto)
    }

    override fun update(bankLoanTypeRequestDTO: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO {
       return bankLoanTypeRequestDTO
            .let(bankLoanMapper::toEntity)
            .let(bankLoanTypeDao::update)
            .let(bankLoanTypeResponseMapper::toDto)
    }
}