package com.example.microservice_students.repository

import com.example.microservice_students.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentsRepository : JpaRepository<Student, Long>{
}