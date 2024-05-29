package com.example.microservice_students.controller

import com.example.microservice_students.model.Student
import com.example.microservice_students.service.CreateStudentsService
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
    lateinit var createStudentsService: CreateStudentsService

    @InjectMockKs
    lateinit var controller: StudentsController

    private var studentMock: Student = Student(1L, "Leo", LocalDate.parse("2020-10-16"))

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldReturnStatusCode201_whenCreatedStudents() {
        every { createStudentsService.createStudents(studentMock) } returns studentMock

        val resultController = controller.createStudents(studentMock)

        assertEquals(HttpStatus.CREATED, resultController.statusCode)
    }


}