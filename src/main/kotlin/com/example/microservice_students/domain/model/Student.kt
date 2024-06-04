package com.example.microservice_students.domain.model

import jakarta.persistence.*
import java.time.LocalDate

@Table(name = "Students")
@Entity(name = "Student")
data class Student(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,
        val name: String,
        val dateBirth: LocalDate
)
