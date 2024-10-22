package com.example.demo.controller

import com.example.demo.config.DbCleanupService
import com.example.demo.config.PostgreSqlContainerInitializer
import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.dto.bankloantype.response.BankLoanTypeDetailsResponseDTO
import com.example.demo.dto.step.StepDTO
import com.example.demo.exception.specific.ErrorCode
import com.example.demo.model.bankloantype.BankLoanType
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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.test.web.servlet.get
import java.io.File


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

    @BeforeAll
    fun initDB() {
        dbCleanupService.truncate()
    }

    @AfterAll
    fun cleanup() {
        dbCleanupService.truncate()
    }

    @ParameterizedTest
    @Order(1)
    @CsvFileSource(files = ["src/test/resources/files/csv/create_bank_loan_files.csv"])
    fun `Create - successful - 201`(inputFileUrl: String, outputFileUrl: String) {
        val count = bankLoanTypeRepository.findAll().size

        val requestBody: String = File(inputFileUrl).readText()

        val actualResponse = mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = requestBody
        }.andExpect {
            status { isCreated() }
        }.andReturn().response.contentAsString

        val expectedResponse = File(outputFileUrl).readText()
        assertThat(objectMapper.readValue<BankLoanTypeRequestDTO>(actualResponse))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(objectMapper.readValue<BankLoanTypeRequestDTO>(expectedResponse))

        val savedLoanTypes = provideBankLoans()
        assertThat(savedLoanTypes).hasSize(count + 1)
    }

    @Test
    @Order(2)
    fun `Create - already existing bank loan name - 400`() {
        val existingName = bankLoanTypeRepository.findAll().first().name

        val bankLoanTypeRequestDTO = BankLoanTypeRequestDTO(
            existingName,
            mutableSetOf(
                StepDTO("Collecting documents", 1, 14, 1),
                StepDTO("Verify documents", 2, 7, 2)
            )
        )

        val actualResponse = mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bankLoanTypeRequestDTO)
        }.andExpect {
            status { isBadRequest() }
        }.andReturn().response.contentAsString

        assertThat(actualResponse).contains(ErrorCode.BANK_LOAN_TYPE_NAME_ALREADY_EXISTS_ERROR)
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("provideBankLoans")
    fun `Find by id - successful - 200`(bankLoanType: BankLoanType) {
        val response = mockMvc.get("$url/${bankLoanType.id}")
            .andExpect {
                status { isOk() }
            }.andReturn().response.contentAsString

        assertThat(objectMapper.readValue<BankLoanTypeDetailsResponseDTO>(response).id).isEqualTo(bankLoanType.id)
    }

    @Test
    @Order(4)
    fun `Find by id - not found - 404`() {
        val id = 999L
        val actualResponse = mockMvc.get("$url/$id")
            .andExpect { status { isNotFound() } }
            .andReturn().response.contentAsString

        assertThat(actualResponse).contains(ErrorCode.BANK_LOAN_TYPE_DOES_NOT_EXIST_ERROR)
    }

    fun provideBankLoans(): List<BankLoanType> {
        return bankLoanTypeRepository.findAll()
    }
}