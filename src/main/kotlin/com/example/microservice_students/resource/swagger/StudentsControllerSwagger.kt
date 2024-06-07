package com.example.microservice_students.resource.swagger

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.application.controller.response.StudentsResponse
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.exception.response.ErrorResponse
import com.example.microservice_students.domain.model.Grades
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@OpenAPIDefinition(info = Info(
        title = "Serviço de estudantes",
        description = "Aplicação desenvolvida para realização de CRUD voltada ao estudo de Kotlin com Spring Boot",
        version = "v0.0.1"))
interface StudentsControllerSwagger {

    @Operation(summary = "Criação de estudante",
                description = "Endpoint responsável por criar estudante",
                tags = ["Criação de estudante"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "400",
        description = "Estudante já cadastrado",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
    @PostMapping
    fun createStudents(@RequestBody student: Student): ResponseEntity<StudentsResponse>

    @Operation(summary = "Busca estudante por id",
        description = "Endpoint responsável por buscar estudante por id",
        tags = ["Buscar por id"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "404",
        description = "Estudante não encontrado",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
    @GetMapping("/{id}")
    fun getStudentById(@Parameter(name = "id", required = true, ) id: Long): ResponseEntity<StudentsResponse>

    @Operation(summary = "Buscar todos estudantes",
                description = "Endpoint responsável por retornar todos os estudantes cadastrados",
                tags = ["Buscar todos estudantes"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "404",
                description = "Estudantes não encontrado",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
    @GetMapping
    fun getAllStudents(@Parameter(required = false, example = "100") page: Int,
                       @Parameter(required = false, example = "1") pageSize: Int): ResponseEntity<StudentsResponse>

    @Operation(summary = "Atualizar data de nascimento",
                description = "Endpoint responsável por atualizar data de nascimento dos estudantes cadastrados",
                tags = ["Atualizar data de nascimento"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
                content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @PutMapping("/{id}")
    fun updateStudent(@Parameter(required = false, example = "1") id: Long,
                      @RequestBody birthRequest: UpdateDateBirthRequest): ResponseEntity<StudentsResponse>

    @Operation(summary = "Remover estudantes",
                description = "Endpoint responsável por remover estudante cadastrado",
                tags = ["Remoção de estudante"])
    @ApiResponse(responseCode = "204", description = "Retorno remoção de estudante com sucesso",
                content = [Content(mediaType = "application/json")])
    @DeleteMapping("/{id}")
    fun removeStudents(@Parameter(required = false, example = "1") id: Long): ResponseEntity<Any>

    @Operation(summary = "Criação de nota para estudantes",
        description = "Endpoint responsável por criar nota dos estudantes",
        tags = ["Criação de nota"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "400",
        description = "Notas já cadastradas",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
    @PostMapping("/grades-students")
    fun createGrades(@RequestBody gradesRequest: Grades): ResponseEntity<StudentsResponse>

    @Operation(summary = "Buscar todas notas",
        description = "Endpoint responsável por retornar todas as notas dos estudantes",
        tags = ["Buscar todas notas"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "404",
        description = "Notas não cadastradas",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
    @GetMapping
    fun getAllGrades(@RequestParam(required = false) page: Int,
                     @RequestParam(required = false) pageSize: Int): ResponseEntity<StudentsResponse>

}