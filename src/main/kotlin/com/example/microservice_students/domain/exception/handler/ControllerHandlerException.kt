package com.example.microservice_students.domain.exception.handler

import com.example.microservice_students.domain.exception.StudentsAlreadyCreatedException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.domain.exception.SubjectInvalidException
import com.example.microservice_students.domain.exception.response.ErrorResponse
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import feign.FeignException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerHandlerException {

    @ExceptionHandler
    fun handleStudentsExistsException(ex: StudentsAlreadyCreatedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                ex.httpStatus.name.plus(" - ").plus(ex.httpStatus.value()), ex.message
            ), ex.httpStatus)
    }

    @ExceptionHandler
    fun handleStudentsNotFoundException(ex: StudentsNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                ex.httpStatus.name.plus(" - ").plus(ex.httpStatus.value()), ex.message
            ), ex.httpStatus)
    }

    @ExceptionHandler
    fun handleFeignExceptionBadRequest(ex: FeignException.BadRequest): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.BAD_REQUEST.name.plus(" - ").plus(HttpStatus.BAD_REQUEST.value()), ex.message
            ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleFeignExceptionNotFound(ex: FeignException.NotFound): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.NOT_FOUND.name.plus(" - ").plus(HttpStatus.NOT_FOUND.value()), ex.message
            ), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleFeignExceptionRetryable(ex: feign.RetryableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name.plus(" - ").plus(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                ex.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun handleCallNotPermittedException(ex: CallNotPermittedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.name.plus(" - ").plus(HttpStatus.SERVICE_UNAVAILABLE.value()), ex.message
            ), HttpStatus.SERVICE_UNAVAILABLE)
    }

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.BAD_REQUEST.name.plus(" - ").plus(HttpStatus.BAD_REQUEST.value()), ex.fieldError?.defaultMessage
            ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.BAD_REQUEST.name.plus(" - ").plus(HttpStatus.BAD_REQUEST.value()),
                String.format("O campo %s não pode ser nulo", (ex.cause as MismatchedInputException).path.map { f -> f.fieldName })
            ), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleSubjectInvalidException(ex: SubjectInvalidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                ex.httpStatus.name.plus(" - ").plus(ex.httpStatus.value()), ex.message), ex.httpStatus)
    }

}