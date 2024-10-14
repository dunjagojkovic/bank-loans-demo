package com.example.demo.controller

import com.example.demo.dto.bankloantype.BankLoanTypeDTO
import com.example.demo.service.bankloantype.BankLoanTypeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bank-loan-types")
class BankLoanTypeController(
    private val bankLoanTypeService: BankLoanTypeService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody bankLoanTypeDTO: BankLoanTypeDTO): BankLoanTypeDTO{
        return bankLoanTypeDTO.let(bankLoanTypeService::create)
    }
}