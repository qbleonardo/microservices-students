package com.example.microservice_students.domain.exception.handler

import com.example.microservice_students.domain.exception.StudentsExistsException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.domain.exception.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerHandlerException {

    @ExceptionHandler
    fun handleStudentsExistsException(ex: StudentsExistsException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
                ex.httpStatus.name.plus( " - ").plus(ex.httpStatus.value())), ex.httpStatus)
    }

    @ExceptionHandler
    fun handleStudentsNotFoundException(ex: StudentsNotFoundException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
                ex.httpStatus.name.plus( " - ").plus(ex.httpStatus.value())), ex.httpStatus)
    }
}