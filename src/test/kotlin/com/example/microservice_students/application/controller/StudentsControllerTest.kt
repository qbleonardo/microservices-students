package com.example.microservice_students.application.controller

import com.example.microservice_students.application.controller.request.UpdateDateBirthRequest
import com.example.microservice_students.domain.service.GradesService
import com.example.microservice_students.domain.service.StudentsService
import com.example.microservice_students.fixture.GradesFixture.getGradesRequest
import com.example.microservice_students.fixture.GradesFixture.getGradesResponse
import com.example.microservice_students.fixture.StudentsFixture.getStudentsFixture
import com.example.microservice_students.fixture.StudentsFixture.getStudentsListFixture
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

    @MockK
    lateinit var gradesService: GradesService

    @InjectMockKs
    lateinit var controller: StudentsController

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldReturnStatusCode201_whenCreatedStudents() {
        every { studentsService.createStudents(getStudentsFixture()) } returns getStudentsFixture()

        val resultController = controller.createStudents(getStudentsFixture())

        assertEquals(HttpStatus.CREATED, resultController.statusCode)
    }

    @Test
    fun shouldReturnStatusCode201_whenCreatedGrades() {
        every { gradesService.executeService(getGradesRequest()) } returns getGradesResponse()

        val resultController = controller.createGrades(getGradesRequest())

        assertEquals(HttpStatus.CREATED, resultController.statusCode)
    }

    @Test
    fun shouldReturnStatusCode200_whenFoundStudents(){
        every { studentsService.getAllStudents(0,1) } returns getStudentsListFixture()

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