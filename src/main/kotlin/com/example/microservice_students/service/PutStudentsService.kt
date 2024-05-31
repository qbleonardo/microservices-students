package com.example.microservice_students.service

import com.example.microservice_students.repository.StudentsRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PutStudentsService(private var studentsRepository: StudentsRepository) {

    fun updateStudentsDateBirth(dateBirth: LocalDate, id: Long) {
         studentsRepository.updateByDateBirth(dateBirth, id)
    }
}