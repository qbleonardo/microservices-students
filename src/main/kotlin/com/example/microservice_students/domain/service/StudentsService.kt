package com.example.microservice_students.domain.service

import com.example.microservice_students.exception.StudentsExistsException
import com.example.microservice_students.exception.StudentsNotFoundException
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.repository.StudentsRepository
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class StudentsService(private val studentsRepository: StudentsRepository) {
    fun createStudents(student: Student): Student {
        if (studentsRepository.existsByNameAndDateBirth(student.name, student.dateBirth)) {
            throw StudentsExistsException("O aluno: [${student.name}], já existe na base de dados.", HttpStatus.BAD_REQUEST)
        }

        return studentsRepository.save(student)
    }

    fun getAllStudents(page: Int, pageSize: Int) : List<Student> {
        val pageRequest = PageRequest.of(page, pageSize)

        val findAll = studentsRepository.findAll(pageRequest)

        if (findAll.isEmpty) {
            throw StudentsNotFoundException("Não há estudantes na base de dados.", HttpStatus.NOT_FOUND)
        }

        return findAll.toList()
    }

    fun updateStudentsDateBirth(dateBirth: LocalDate, id: Long) {
        studentsRepository.updateByDateBirth(dateBirth, id)
    }

    fun removeStudentsById(id: Long){
        studentsRepository.deleteById(id)
    }
}