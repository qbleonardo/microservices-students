package com.example.microservice_students.controller

import com.example.microservice_students.controller.response.StudentsResponse
import com.example.microservice_students.model.Student
import com.example.microservice_students.service.CreateStudentsService
import com.example.microservice_students.service.DeleteStudentsService
import com.example.microservice_students.service.FindStudentsService
import com.example.microservice_students.service.PutStudentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(value = ["/students"], produces = ["application/json"])
class StudentsController(private var createStudentsService: CreateStudentsService,
                         private var findStudentsService: FindStudentsService,
                         private var putStudentsService: PutStudentsService,
                         private var deleteStudentsService: DeleteStudentsService) {

    @PostMapping("/create")
    fun createStudents(@RequestBody student: Student): ResponseEntity<StudentsResponse> {
        val createdStudent = createStudentsService.createStudents(student)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                    .body(StudentsResponse("Estudante criado",
                            "Id: ${createdStudent.id} / Nome: ${createdStudent.name} / Data de nascimento: ${createdStudent.dateBirth}", HttpStatus.CREATED.value()))
    }

    @GetMapping("/all")
    fun getAllStudents(@RequestParam(required = false) page: Int,
                       @RequestParam(required = false) pageSize: Int): ResponseEntity<StudentsResponse> {
        val pageSizeValue: Int = if(pageSize < 1) 1 else pageSize

        val allStudents = findStudentsService.getAllStudents(page, pageSizeValue)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Estudantes encontrados",
                        allStudents.joinToString { s -> "Id: ${s.id}".plus(" - Nome: ${s.name}").plus( " - Data de nascimento: ${s.dateBirth}")}, HttpStatus.OK.value()))
    }

    @PutMapping("/update/{id}")
    fun updateStudent(@PathVariable(value = "id") id: Long,
                      @RequestParam dateBirth: LocalDate) : ResponseEntity<StudentsResponse>{
        putStudentsService.updateStudentsDateBirth(dateBirth, id)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Data de nascimento do estudante atualizada",
                        "Id: $id - Nova data de nascimento: $dateBirth", HttpStatus.OK.value()))
    }

    @DeleteMapping("/{id}")
    fun removeStudents(@PathVariable(value = "id") id: Long): ResponseEntity<StudentsResponse>{
        deleteStudentsService.removeStudentsById(id)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StudentsResponse("Estudante deletado", "Id: $id", HttpStatus.OK.value()))
    }

}