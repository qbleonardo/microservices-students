package com.example.microservice_students.domain.exception

import org.springframework.http.HttpStatus

class StudentsNotFoundException(message: String, val httpStatus: HttpStatus) : RuntimeException(message) {
}