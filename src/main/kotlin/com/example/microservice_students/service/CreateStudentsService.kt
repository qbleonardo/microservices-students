package com.example.microservice_students.service

import com.example.microservice_students.exception.StudentsExistsException
import com.example.microservice_students.model.Student
import com.example.microservice_students.repository.StudentsRepository
import org.springframework.stereotype.Service

@Service
class CreateStudentsService(private val studentsRepository: StudentsRepository) {
    fun createStudents(student: Student): Student {
        if (studentsRepository.existsByNameAndDateBirth(student.name, student.dateBirth)) {
            throw StudentsExistsException("O aluno: [${student.name}], já existe na base de dados.")
        }

        return studentsRepository.save(student)
    }
}