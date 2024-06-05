package com.example.microservice_students.domain.service

import com.example.microservice_students.fixture.GradesFixture.getGradesRequest
import com.example.microservice_students.fixture.GradesFixture.getGradesResponse
import com.example.microservice_students.resource.feign.GradesStudentsFeign
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GradesServiceTest {

    @MockK
    lateinit var gradesStudentsFeign: GradesStudentsFeign

    @InjectMockKs
    lateinit var gradesService: GradesService

    @BeforeEach
    fun setUp(): Unit = MockKAnnotations.init(this)

    @Test
    fun shouldSaveGrade_whenRequestToGradeService_returnEqualsObject() {
        every { gradesStudentsFeign.createGradesToStudents(getGradesRequest()) } returns getGradesResponse()

        val response = gradesService.executeService(getGradesRequest())

        val createdGrade = gradesStudentsFeign.createGradesToStudents(getGradesRequest())

        Assertions.assertEquals(createdGrade.id, response.id)
    }
}