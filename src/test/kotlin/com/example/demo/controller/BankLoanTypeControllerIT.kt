package com.example.demo.controller

import com.example.demo.config.DbCleanupService
import com.example.demo.config.PostgreSqlContainerInitializer
import com.example.demo.dao.bankloantype.BankLoanTypeDao
import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.dto.step.StepDTO
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
import org.junit.jupiter.api.*


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
    private val bankLoanTypeDao: BankLoanTypeDao,
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
    fun `Create - successful`() {
        val bankLoanTypeRequestDTO = BankLoanTypeDTO(
            "Cash loan",
            mutableSetOf(
                StepDTO("Collecting documents", 1, 14, 1),
                StepDTO("Verify documents", 2, 7, 2)
            ),
            1
        )

        val response = mockMvc.post("/api/bank-loan-types") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bankLoanTypeRequestDTO)
        }.andExpect {
            status { isCreated() }
        }.andReturn().response.contentAsString

        val createdBankLoanType: BankLoanTypeDTO = objectMapper.readValue(response, BankLoanTypeDTO::class.java)

        val savedLoanTypes = bankLoanTypeRepository.findAll()
        assertThat(savedLoanTypes).hasSize(1)

        assertThat(createdBankLoanType.name).isEqualTo(bankLoanTypeRequestDTO.name)
        assertThat(createdBankLoanType.steps.toList())
            .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
            .containsExactlyInAnyOrderElementsOf(bankLoanTypeRequestDTO.steps.toList())
    }

    @Test
    fun `Create - already existing name returns 400`() {
        // Pokušaj da kreiraš novi tip sa istim imenom
        val newBankLoanTypeRequestDTO = BankLoanTypeDTO(
            "Cash loan", // Ime koje se ponavlja
            mutableSetOf(
                StepDTO("Collecting documents", 1, 14, 1),
                StepDTO("Verify documents", 2, 7, 2)
            ),
            1
        )

        mockMvc.post("/api/bank-loan-types") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBankLoanTypeRequestDTO)
        }.andExpect {
            status { isBadRequest() } // Očekuje status 400
        }
    }

}