package com.example.demo.controller

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.dto.bankloantype.BankLoanTypeDetailsDTO
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
    fun create(@RequestBody bankLoanTypeDTO: BankLoanTypeDTO): BankLoanTypeDTO {
        return bankLoanTypeService.create(bankLoanTypeDTO)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): BankLoanTypeDetailsDTO {
        return bankLoanTypeService.findById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        bankLoanTypeService.delete(id)
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchByName(@RequestParam name: String): List<BankLoanTypeDTO> {
        return bankLoanTypeService.findByName(name)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable id: Long, @RequestBody bankLoanTypeDTO: BankLoanTypeDTO): BankLoanTypeDTO{
        return bankLoanTypeDTO
            .apply { this.id = id }
            .let { bankLoanTypeService.update(bankLoanTypeDTO) }
    }
}