package com.example.microservice_students.domain.exception.response

data class ErrorResponse(val code: String, val message: String? = null) {
}