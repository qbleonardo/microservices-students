package com.example.microservice_students.domain.model.enum

import com.example.microservice_students.domain.exception.SubjectInvalidException
import org.springframework.http.HttpStatus

enum class SubjectEnum(val subject: String) {
    MATEMATICA("Matematica"),
    PORTUGUES("Portugues"),
    HISTORIA("Historia"),
    GEOGRAFIA("Geografia"),
    FISICA("Fisica"),
    QUIMICA("Quimica"),
    EDUCACAO_FISICA("Educacao Fisica");

    companion object {
        fun validateSubject(value: String) {
            val isValid = entries.any { it.subject == value || it.name == value }

            if (!isValid) {
                throw SubjectInvalidException(
                    "Matéria inválida [$value], revisar matéria informada para cadastro de nota",
                    HttpStatus.BAD_REQUEST)
            }
        }
    }
}