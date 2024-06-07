package com.example.microservice_students.domain.service

import com.example.microservice_students.domain.exception.StudentsAlreadyCreatedException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.resource.repository.StudentsRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StudentsService(private val studentsRepository: StudentsRepository) {
    fun createStudents(student: Student): Student {
        if (studentsRepository.existsByNameAndDateBirth(student.name, student.dateBirth)) {
            throw StudentsAlreadyCreatedException(
                "O aluno: [${student.name}] está cadastrado na base de dados.",
                HttpStatus.BAD_REQUEST
            )
        }
        return studentsRepository.save(student)
    }

    fun getStudentsById(id: Long): Student {
        return getStudentByIdOrThrowException(id)
    }

    fun getAllStudents(page: Int, pageSize: Int): List<Student> {
        val pageRequest = PageRequest.of(page, pageSize)

        val findAll = studentsRepository.findAll(pageRequest)

        if (findAll.isEmpty) {
            throw StudentsNotFoundException("Não há estudantes na base de dados.", HttpStatus.NOT_FOUND)
        }

        return findAll.toList()
    }

    fun updateStudentsDateBirth(dateBirth: LocalDate, id: Long) {
        val student = getStudentByIdOrThrowException(id)

        studentsRepository.updateByDateBirth(dateBirth, student.id)
    }

    fun removeStudentsById(id: Long) {
        val student = getStudentByIdOrThrowException(id)

        studentsRepository.deleteById(student.id)
    }

    private fun getStudentByIdOrThrowException(id: Long): Student {
        return studentsRepository.findById(id)
            .orElseThrow {
                throw StudentsNotFoundException(
                    "Não há o estudante com id: [$id] na base de dados.",
                    HttpStatus.NOT_FOUND
                )
            }
    }
}