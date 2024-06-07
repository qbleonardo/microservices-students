package com.example.microservice_students.resource.repository

import com.example.microservice_students.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Repository
interface StudentsRepository : JpaRepository<Student, Long> {
    fun existsByNameAndDateBirth(name: String, dateBirth: LocalDate): Boolean

    @Modifying
    @Transactional
    @Query("update Student s set s.dateBirth = ?1 where s.id = ?2")
    fun updateByDateBirth(dateBirth: LocalDate, id: Long)
}