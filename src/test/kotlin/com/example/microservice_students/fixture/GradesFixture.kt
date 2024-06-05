package com.example.microservice_students.fixture

import com.example.microservice_students.application.controller.request.GradesRequest
import com.example.microservice_students.application.controller.response.GradesResponse

object GradesFixture {

    fun getGradesRequest(): GradesRequest{
        return GradesRequest("Fulano", "Matemática", 5)
    }

    fun getGradesResponse(): GradesResponse{
        return GradesResponse(1L,"Fulano", "Matemática", 5)
    }
}