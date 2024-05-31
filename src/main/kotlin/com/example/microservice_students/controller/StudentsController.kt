package com.example.microservice_students.controller

import com.example.microservice_students.model.Student
import com.example.microservice_students.service.CreateStudentsService
import com.example.microservice_students.service.DeleteStudentsService
import com.example.microservice_students.service.FindStudentsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/students"], produces = ["application/json"])
class StudentsController(private var createStudentsService: CreateStudentsService,
                         private var findStudentsService: FindStudentsService,
                         private var deleteStudentsService: DeleteStudentsService) {

    @PostMapping("/create")
    fun createStudents(@RequestBody student: Student): ResponseEntity<String> {
        val createdStudent = createStudentsService.createStudents(student)

        return ResponseEntity
                .status(HttpStatus.CREATED)
                    .body(String.format("Estudante criado. Nome: ${createdStudent.name} / Id: ${createdStudent.id}"))
    }

    @GetMapping("/all")
    fun getAllStudents(@RequestParam(required = false) page: Int,
                       @RequestParam(required = false) pageSize: Int): ResponseEntity<String> {
        val pageSizeValue: Int = if(pageSize < 1) 1 else pageSize

        val allStudents = findStudentsService.getAllStudents(page, pageSizeValue)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Estudantes encontrados: \n " +
                        allStudents.joinToString
                        { s -> "Id: ".plus(s.id).plus(" - Nome: ").plus(s.name) }))
    }

    @DeleteMapping("/{id}")
    fun removeStudents(@PathVariable(value = "id") id: Long): ResponseEntity<String>{
        deleteStudentsService.removeStudentsById(id)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Estudante deletado. Id: $id")
    }

}