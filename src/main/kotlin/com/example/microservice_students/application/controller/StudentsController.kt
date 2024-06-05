package com.example.microservice_students.application.controller

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.application.controller.response.StudentsResponse
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.service.StudentsService
import com.example.microservice_students.resource.swagger.StudentsControllerSwagger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/students"], produces = [MediaType.APPLICATION_JSON_VALUE])
class StudentsController(private var studentsService: StudentsService) : StudentsControllerSwagger {

    @PostMapping
    override fun createStudents(@RequestBody student: Student): ResponseEntity<StudentsResponse> {
        val createdStudent = studentsService.createStudents(student)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                    .body(StudentsResponse("Estudante criado",
                            Student(createdStudent.id, createdStudent.name,createdStudent.dateBirth), HttpStatus.CREATED.value()))
    }

    @GetMapping
    override fun getAllStudents(@RequestParam(required = false) page: Int,
                                @RequestParam(required = false) pageSize: Int): ResponseEntity<StudentsResponse> {
        val pageSizeValue: Int = if(pageSize < 1) 1 else pageSize

        val allStudents = studentsService.getAllStudents(page, pageSizeValue)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Estudantes encontrados",
                        allStudents, HttpStatus.OK.value()))
    }

    @PutMapping("/{id}")
    override fun updateStudent(@PathVariable(value = "id") id: Long,
                               @RequestBody birthRequest: UpdateDateBirthRequest) : ResponseEntity<StudentsResponse>{
        studentsService.updateStudentsDateBirth(birthRequest.dateBirth, id)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Data de nascimento do estudante atualizada",
                        String.format("Id: $id - Nova data de nascimento: ${birthRequest.dateBirth}"), HttpStatus.OK.value()))
    }

    @DeleteMapping("/{id}")
    override fun removeStudents(@PathVariable(value = "id") id: Long): ResponseEntity<Any>{
        studentsService.removeStudentsById(id)

        return ResponseEntity.noContent().build()
    }

}