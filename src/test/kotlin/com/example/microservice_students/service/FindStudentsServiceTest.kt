package com.example.microservice_students.service

import com.example.microservice_students.exception.StudentsNotFoundException
import com.example.microservice_students.model.Student
import com.example.microservice_students.repository.StudentsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

class FindStudentsServiceTest {

    @MockK
    lateinit var studentsRepository: StudentsRepository

    @InjectMockKs
    lateinit var findStudentsService: FindStudentsService

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldReturnSameSizeChoiceAndStatusOK() {
        val studentList = listOf(Student(2, "A", LocalDate.parse("2020-01-02")))

        val pageImpl = PageImpl(studentList)

        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns pageImpl

        val allStudents = findStudentsService.getAllStudents(0, 1)

        Assertions.assertEquals(1, allStudents.size)
    }

    @Test
    fun shouldThrowStudentsNotFoundException(){
        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns Page.empty()

        assertThrows<StudentsNotFoundException> { findStudentsService.getAllStudents(0, 1) }
    }
}