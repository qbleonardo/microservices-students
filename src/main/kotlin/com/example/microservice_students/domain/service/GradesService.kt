package com.example.microservice_students.domain.service

import com.example.microservice_students.domain.model.Grades
import com.example.microservice_students.resource.feign.GradesStudentsFeign
import com.example.microservice_students.resource.log.LogFactory.log
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Service

@Service
@Retry(name = "studentsRetry")
@CircuitBreaker(name = "studentsService")
class GradesService(private var gradesStudentsFeign: GradesStudentsFeign) {

     fun executeCreateGrade(gradesRequest: Grades): Grades {
         log.info("Realizando chamada ao serviço de notas para cadastrado de nota")

        return gradesStudentsFeign.createGradesToStudents(gradesRequest)
    }

    fun executeGetAllGrades(page: Int, pageSize: Int): List<Grades>{
        log.info("Realizando chamada ao serviço de notas para buscar todas as notas")

        return gradesStudentsFeign.getAllGrades(page, pageSize)
    }
}