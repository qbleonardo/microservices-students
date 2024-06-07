package com.example.microservice_students.domain.exception.handler

import com.example.microservice_students.domain.exception.StudentsAlreadyCreatedException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.domain.exception.response.ErrorResponse
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerHandlerException {

    @ExceptionHandler
    fun handleStudentsExistsException(ex: StudentsAlreadyCreatedException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
                ex.httpStatus.name.plus( " - ").plus(ex.httpStatus.value())), ex.httpStatus)
    }

    @ExceptionHandler
    fun handleStudentsNotFoundException(ex: StudentsNotFoundException): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
                ex.httpStatus.name.plus( " - ").plus(ex.httpStatus.value())), ex.httpStatus)
    }

    @ExceptionHandler
    fun handleFeignExceptionBadRequest(ex: FeignException.BadRequest): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
            HttpStatus.BAD_REQUEST.name.plus( " - ").plus(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleFeignExceptionNotFound(ex: FeignException.NotFound): ResponseEntity<ErrorResponse>{
        return ResponseEntity(ErrorResponse(ex.message,
            HttpStatus.NOT_FOUND.name.plus( " - ").plus(HttpStatus.NOT_FOUND.value())), HttpStatus.NOT_FOUND)
    }
}