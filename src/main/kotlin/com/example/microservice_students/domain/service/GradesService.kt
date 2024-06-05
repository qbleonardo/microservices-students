package com.example.microservice_students.domain.service

import com.example.microservice_students.application.controller.request.GradesRequest
import com.example.microservice_students.application.controller.response.GradesResponse
import com.example.microservice_students.resource.feign.GradesStudentsFeign
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class GradesService(private var gradesStudentsFeign: GradesStudentsFeign) {

    @CircuitBreaker(name = "studentsService")
     fun executeService(gradesRequest: GradesRequest): GradesResponse {
        return gradesStudentsFeign.createGradesToStudents(gradesRequest)
    }
}