package com.example.demo.controller

import com.example.demo.config.DbCleanupService
import com.example.demo.config.PostgreSqlContainerInitializer
import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.dto.bankloantype.BankLoanTypeDetailsDTO
import com.example.demo.dto.step.StepDTO
import com.example.demo.model.bankloantype.BankLoanType
import com.example.demo.model.step.Step
import com.example.demo.repository.bankloantype.BankLoanTypeRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.test.Test
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.*
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.get


@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [PostgreSqlContainerInitializer::class])
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BankLoanTypeControllerIT @Autowired constructor(
    private val mockMvc: MockMvc,
    private val dbCleanupService: DbCleanupService,
    private val objectMapper: ObjectMapper,
    private val bankLoanTypeRepository: BankLoanTypeRepository
) {
    private final val url = "/api/bank-loan-types"
    lateinit var bankLoanTypeResponse: BankLoanTypeDTO

    @BeforeAll
    fun initDB() {
        dbCleanupService.truncate()
    }

    @AfterAll
    fun cleanup() {
        dbCleanupService.truncate()
    }

    @Test
    @Order(1)
    fun `Create - successful - 201`() {
        val bankLoanTypeRequestDTO = BankLoanTypeDTO(
            "Cash loan",
            mutableSetOf(
                StepDTO("Collecting documents", 1, 14),
                StepDTO("Verify documents", 2, 7)
            )
        )

        val response = mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bankLoanTypeRequestDTO)
        }.andExpect {
            status { isCreated() }
        }.andReturn().response.contentAsString

        val createdBankLoanType: BankLoanTypeDTO = objectMapper.readValue(response, BankLoanTypeDTO::class.java)

        val savedLoanTypes = bankLoanTypeRepository.findAll()
        assertThat(savedLoanTypes).hasSize(1)

        savedLoanTypes.forEach { println("Saved Loan Type ID: ${it.id}") } // Print all saved IDs

        assertThat(createdBankLoanType.name).isEqualTo(bankLoanTypeRequestDTO.name)
        assertThat(createdBankLoanType.steps.toList())
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
            .containsExactlyInAnyOrderElementsOf(bankLoanTypeRequestDTO.steps.toList())
    }

    @Test
    @Order(2)
    fun `Create - already existing bank loan name - 400`() {
        val newBankLoanTypeRequestDTO = BankLoanTypeDTO(
            "Cash loan",
            mutableSetOf(
                StepDTO("Collecting documents", 1, 14, 1),
                StepDTO("Verify documents", 2, 7, 2)
            ),
            1
        )

        val response = mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBankLoanTypeRequestDTO)
        }.andExpect {
            status { isBadRequest() }
        }.andReturn().response.contentAsString

        assertThat(response).contains("Bank loan type with name[${newBankLoanTypeRequestDTO.name}] already exists!")
    }

    @Test
    @Order(3)
    fun `Find by id - successful - 200`() {
        val allBankLoans = bankLoanTypeRepository.findAll()

        val response = mockMvc.get("$url/${allBankLoans[0].id}")
            .andExpect { status { isOk() } }
            .andReturn().response.contentAsString

        val foundBankLoan = objectMapper.readValue(response, BankLoanTypeDTO::class.java)
        assertThat(foundBankLoan.name).isEqualTo(allBankLoans[0].name)
    }

    @Test
    @Order(4)
    fun `Find by id - not found - 404`() {
        val id = 999L
        mockMvc.get("$url/$id")
            .andExpect {
                status { HttpStatus.NOT_FOUND }
            }
    }

}