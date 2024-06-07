package com.example.microservice_students.resource.feign

import com.example.microservice_students.domain.model.Grades
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GradesStudentsServices", url = "\${feign.grades-service.url}")
interface GradesStudentsFeign {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGradesToStudents(@RequestBody gradesRequest: Grades): Grades

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllGrades(@RequestParam(required = false) page: Int,
                     @RequestParam(required = false) pageSize: Int): List<Grades>
}