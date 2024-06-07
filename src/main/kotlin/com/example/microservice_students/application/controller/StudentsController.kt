package com.example.microservice_students.application.controller

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.application.controller.response.StudentsResponse
import com.example.microservice_students.domain.model.Grades
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.service.GradesService
import com.example.microservice_students.domain.service.StudentsService
import com.example.microservice_students.domain.util.ValidatePageSizeUtils.validatePageSize
import com.example.microservice_students.resource.swagger.StudentsControllerSwagger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/students"], produces = [MediaType.APPLICATION_JSON_VALUE])
class StudentsController(private var studentsService: StudentsService,
                         private var gradesService: GradesService) : StudentsControllerSwagger {

    @PostMapping
    override fun createStudents(@RequestBody  student: Student): ResponseEntity<StudentsResponse> {
        val createdStudent = studentsService.createStudents(student)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                    .body(StudentsResponse("Estudante criado",
                            Student(createdStudent.id, createdStudent.name,createdStudent.dateBirth)))
    }

    @GetMapping("/{id}")
    override fun getStudentById(@PathVariable(value = "id", required = true) id: Long): ResponseEntity<StudentsResponse> {
        val studentById = studentsService.getStudentsById(id)

        return ResponseEntity
                .status(HttpStatus.OK)
                    .body(StudentsResponse("Estudante encontrado", studentById))
    }

    @GetMapping
    override fun getAllStudents(@RequestParam(required = false) page: Int,
                                @RequestParam(required = false) pageSize: Int): ResponseEntity<StudentsResponse> {
        val pageSizeValue: Int = pageSize.validatePageSize()

        val allStudents = studentsService.getAllStudents(page, pageSizeValue)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Estudantes encontrados", allStudents))
    }

    @PutMapping("/{id}")
    override fun updateStudent(@PathVariable(value = "id") id: Long,
                               @RequestBody birthRequest: UpdateDateBirthRequest) : ResponseEntity<StudentsResponse>{
        studentsService.updateStudentsDateBirth(birthRequest.dateBirth, id)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Data de nascimento do estudante atualizada",
                        String.format("Id: $id - Nova data de nascimento: ${birthRequest.dateBirth}")))
    }

    @DeleteMapping("/{id}")
    override fun removeStudents(@PathVariable(value = "id") id: Long): ResponseEntity<Any>{
        studentsService.removeStudentsById(id)

        return ResponseEntity.noContent().build()
    }

    @PostMapping("/grades-students")
    override fun createGrades(@RequestBody gradesRequest: Grades): ResponseEntity<StudentsResponse>{
        val gradesResponse = gradesService.executeCreateGrade(gradesRequest)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(StudentsResponse("Nota criada", gradesResponse))
    }

    @GetMapping("/grades-students")
    override fun getAllGrades(@RequestParam(required = false) page: Int,
                              @RequestParam(required = false) pageSize: Int): ResponseEntity<StudentsResponse> {
        val pageSizeValue: Int = pageSize.validatePageSize()

        val allGrades = gradesService.executeGetAllGrades(page, pageSizeValue)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(StudentsResponse("Notas encontradas", allGrades))
    }

}