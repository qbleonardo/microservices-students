package com.example.microservice_students.domain.model

import jakarta.validation.constraints.NotBlank

data class Grade(
        var id: Long,
        @field:NotBlank(message = "Campo nome não pode ser vazio")
        var studentName: String,
        @field:NotBlank(message = "Campo matéria não pode ser vazio")
        var subject: String,
        @field:NotBlank(message = "Campo nota não pode ser vazio")
        var gradeValue: Int
)
