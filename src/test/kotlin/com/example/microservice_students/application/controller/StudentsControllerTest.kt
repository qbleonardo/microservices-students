package com.example.microservice_students.application.controller

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.service.StudentsService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.time.LocalDate

class StudentsControllerTest {

    @MockK
    lateinit var studentsService: StudentsService

    @InjectMockKs
    lateinit var controller: StudentsController

    private var studentMock: Student = Student(1L, "Leo", LocalDate.parse("2020-10-16"))

    private var studentList = listOf(Student(2, "A", LocalDate.parse("2020-01-02")))

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldReturnStatusCode201_whenCreatedStudents() {
        every { studentsService.createStudents(studentMock) } returns studentMock

        val resultController = controller.createStudents(studentMock)

        assertEquals(HttpStatus.CREATED, resultController.statusCode)
    }

    @Test
    fun shouldReturnStatusCode200_whenFoundStudents(){
        every { studentsService.getAllStudents(0,1) } returns studentList

        val resultController = controller.getAllStudents(0, 1)

        assertEquals(HttpStatus.OK, resultController.statusCode)
    }

    @Test
    fun shouldReturnStatusCode200_whenUpdateDateBirth(){
        every { studentsService.updateStudentsDateBirth(LocalDate.parse("2024-01-01"), 1L) } returns Unit

        val resultController = controller.updateStudent(1L, UpdateDateBirthRequest(LocalDate.parse("2024-01-01")))

        assertEquals(HttpStatus.OK, resultController.statusCode)
    }

    @Test
    fun shouldReturnStatusCode200_whenDeleteStudents(){
        every { studentsService.removeStudentsById(1L) } returns Unit

        val resultController = controller.removeStudents(1L)

        assertEquals(HttpStatus.NO_CONTENT, resultController.statusCode)
    }
}