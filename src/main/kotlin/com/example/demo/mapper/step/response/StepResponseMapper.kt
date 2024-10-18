package com.example.demo.mapper.step.response

import com.example.demo.dto.step.StepDTO
import com.example.demo.model.step.Step
import org.springframework.stereotype.Component

@Component
class StepResponseMapper {
    fun toDto(step: Step): StepDTO {
        return with(step) {
            StepDTO(
                name,
                orderNumber,
                expectedDurationDay,
                id
            )
        }
    }
}