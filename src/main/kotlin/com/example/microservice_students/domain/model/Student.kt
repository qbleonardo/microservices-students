package com.example.microservice_students.domain.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

@Table(name = "Students")
@Entity(name = "Student")
data class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        @field:NotBlank(message = "Campo nome não pode ser vazio")
        val name: String,
        val dateBirth: LocalDate
)
