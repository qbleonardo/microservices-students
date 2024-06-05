package com.example.microservice_students.domain.exception.response

data class ErrorResponse(val message: String? = null, val code: String) {
}