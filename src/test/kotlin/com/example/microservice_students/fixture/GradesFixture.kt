package com.example.microservice_students.fixture

import com.example.microservice_students.domain.model.Grade

object GradesFixture {

    fun getGradesResponse(): Grade {
        return Grade(1L,"Fulano", "Matemática", 5)
    }

    fun getGradesListResponse(): List<Grade> {
        return listOf(Grade(1L,"Fulano", "Matemática", 5))
    }
}