package com.example.microservice_students.resource.feign

import com.example.microservice_students.application.controller.request.GradesRequest
import com.example.microservice_students.application.controller.response.GradesResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "GradesStudentsServices", url = "localhost:8081/grades-students")
interface GradesStudentsFeign {

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGradesToStudents(@RequestBody gradesRequest: GradesRequest): GradesResponse
}