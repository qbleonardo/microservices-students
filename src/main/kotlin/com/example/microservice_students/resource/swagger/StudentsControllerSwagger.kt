package com.example.microservice_students.resource.swagger

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.application.controller.response.StudentsResponse
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.exception.response.ErrorResponse
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@OpenAPIDefinition(info = Info(title = "Serviço de estudantes",
        description = "Aplicação desenvolvida para realização de CRUD voltada ao estudo de Kotlin com Spring Boot", version = "v0.0.1"))
interface StudentsControllerSwagger {

    @Operation(summary = "Criação de estudante",
            description = "Endpoint responsável por criar estudante",
            tags = ["Criação de estudante"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
            @PostMapping
            fun createStudents(@RequestBody student: Student): ResponseEntity<StudentsResponse>

    @Operation(summary = "Buscar todos estudantes",
            description = "Endpoint responsável por retornar todos os estudantes cadastrados",
            tags = ["Buscar todos estudantes"])
    @ApiResponse(responseCode = "200", description = "Retorno com sucesso",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = StudentsResponse::class))])
    @ApiResponse(responseCode = "404",
            description = "Estudantes não encontrado",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorResponse::class))])
            @GetMapping
            fun getAllStudents(@Parameter(required = false, example = "100")  page: Int,
                               @Parameter(required = false, example = "1") pageSize: Int): ResponseEntity<StudentsResponse>

    @Operation(summary = "Atualizar data de nascimento",
            description = "Endpoint responsável por atualizar data de nascimento dos estudantes cadastrados",
            tags = ["Atualizar data de nascimento dos estudantes"])
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

}