package com.example.microservice_students.domain.service

import com.example.microservice_students.domain.model.Grades
import com.example.microservice_students.resource.feign.GradesStudentsFeign
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.stereotype.Service

@Service
class GradesService(private var gradesStudentsFeign: GradesStudentsFeign) {

    @CircuitBreaker(name = "studentsService")
     fun executeCreateGrade(gradesRequest: Grades): Grades {
        return gradesStudentsFeign.createGradesToStudents(gradesRequest)
    }

    @CircuitBreaker(name = "studentsService")
    fun executeGetAllGrades(page: Int, pageSize: Int): List<Grades>{
        return gradesStudentsFeign.getAllGrades(page, pageSize)
    }
}