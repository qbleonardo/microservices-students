package com.example.microservice_students.exception

import org.springframework.http.HttpStatus

class StudentsExistsException(message: String, val httpStatus: HttpStatus) : RuntimeException(message)