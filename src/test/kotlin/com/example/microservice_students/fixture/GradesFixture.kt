package com.example.microservice_students.fixture

import com.example.microservice_students.domain.model.Grades

object GradesFixture {

    fun getGradesResponse(): Grades {
        return Grades(1L,"Fulano", "Matemática", 5)
    }
}