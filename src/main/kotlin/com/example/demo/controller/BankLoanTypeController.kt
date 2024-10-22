package com.example.demo.controller

import com.example.demo.dto.bankloantype.request.BankLoanTypeRequestDTO
import com.example.demo.dto.bankloantype.response.BankLoanTypeDetailsResponseDTO
import com.example.demo.service.bankloantype.BankLoanTypeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bank-loan-types")
class BankLoanTypeController(
    private val bankLoanTypeService: BankLoanTypeService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody bankLoanTypeRequestDTO: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO {
        return bankLoanTypeService.create(bankLoanTypeRequestDTO)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): BankLoanTypeDetailsResponseDTO {
        return bankLoanTypeService.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        bankLoanTypeService.delete(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun searchByName(@RequestParam name: String): List<BankLoanTypeRequestDTO> {
        return bankLoanTypeService.findByName(name)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody bankLoanTypeRequestDTO: BankLoanTypeRequestDTO): BankLoanTypeRequestDTO{
        return bankLoanTypeRequestDTO
            .apply { this.id = id }
            .let(bankLoanTypeService::update)
    }
}