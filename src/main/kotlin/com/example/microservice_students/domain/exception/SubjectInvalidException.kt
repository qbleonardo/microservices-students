package com.example.microservice_students.domain.exception

import org.springframework.http.HttpStatus

class SubjectInvalidException(message: String, val httpStatus: HttpStatus) : RuntimeException(message) {
}