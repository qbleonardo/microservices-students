package com.example.microservice_students.service

import com.example.microservice_students.exception.StudentsFoundException
import com.example.microservice_students.model.Student
import com.example.microservice_students.repository.StudentsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class CreateStudentsServiceTest {

    @MockK
    lateinit var studentsRepository: StudentsRepository

    @InjectMockKs
    lateinit var createStudentsService: CreateStudentsService

     private var studentMock: Student = Student(1L, "Leo", LocalDate.parse("2020-10-16"))

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun givenStudentName_whenCreated_shouldToBeEqualsNameSaved() {
        every { studentsRepository.existsByNameAndDateBirth("Leo", LocalDate.parse("2020-10-16")) } returns false

        every { studentsRepository.save(studentMock) } returns studentMock

        val studentCreated = studentsRepository.save(studentMock)

        assertEquals(studentCreated.name, createStudentsService.createStudents(studentMock).name)
    }

    @Test
    fun shouldThrowStudentsFoundException() {
        every { studentsRepository.existsByNameAndDateBirth("Leo", LocalDate.parse("2020-10-16")) } returns true

        assertThrows<StudentsFoundException> { createStudentsService.createStudents(studentMock) }
    }


}