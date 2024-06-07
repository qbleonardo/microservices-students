package com.example.microservice_students.fixture

import com.example.microservice_students.domain.model.Student
import java.time.LocalDate

object StudentsFixture {

    fun getStudentsFixture(): Student {
        return Student(1L, "Leo", LocalDate.parse("2024-10-16"))
    }

    fun getStudentsListFixture(): List<Student> {
        return listOf(Student(2, "A", LocalDate.parse("2024-01-02")))
    }
}