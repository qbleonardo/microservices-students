package com.example.microservice_students.service

import com.example.microservice_students.exception.StudentsNotFoundException
import com.example.microservice_students.model.Student
import com.example.microservice_students.repository.StudentsRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FindStudentsService(val studentsRepository: StudentsRepository) {

    fun getAllStudents(page: Int, pageSize: Int) : List<Student> {
        val pageRequest = PageRequest.of(page, pageSize)

        val findAll = studentsRepository.findAll(pageRequest)

        if (findAll.isEmpty) {
            throw StudentsNotFoundException("Não há estudantes na base de dados.")
        }

        return findAll.toList()
    }
}