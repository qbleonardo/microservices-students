package com.example.microservice_students.model

import java.util.Date

data class Student(
        val id: Long,
        val name: String,
        val dateBirth: Date
)
