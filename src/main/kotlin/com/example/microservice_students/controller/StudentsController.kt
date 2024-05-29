package com.example.microservice_students.controller

import com.example.microservice_students.model.Student
import com.example.microservice_students.service.CreateStudentsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentsController(var createStudentsService: CreateStudentsService) {

    @PostMapping("/create/students")
    fun createStudents(@RequestBody student: Student): ResponseEntity<String> {
        val createdStudent = createStudentsService.createStudents(student)

        return ResponseEntity
                .ok(String.format("Estudante criado. Nome: ${createdStudent.name} / Id: ${createdStudent.id}"))
    }

}