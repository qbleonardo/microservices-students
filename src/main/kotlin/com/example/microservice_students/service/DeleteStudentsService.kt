package com.example.microservice_students.service

import com.example.microservice_students.repository.StudentsRepository
import org.springframework.stereotype.Service

@Service
class DeleteStudentsService(private var studentsRepository: StudentsRepository) {

    fun removeStudentsById(id: Long){
        studentsRepository.deleteById(id)
    }
}