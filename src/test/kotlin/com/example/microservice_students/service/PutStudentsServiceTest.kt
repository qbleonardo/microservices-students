package com.example.microservice_students.service

import com.example.microservice_students.repository.StudentsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PutStudentsServiceTest {

    @MockK
    lateinit var studentsRepository: StudentsRepository

    @InjectMockKs
    lateinit var putStudentsService: PutStudentsService

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun shouldUpdateStudentsByDateBirth() {
        every { studentsRepository.updateByDateBirth(LocalDate.parse("2024-01-01"), 1L) } returns Unit

        putStudentsService.updateStudentsDateBirth(LocalDate.parse("2024-01-01"), 1L)

        verify(atLeast = 1)  { studentsRepository.updateByDateBirth(LocalDate.parse("2024-01-01"), 1L) }
        verify(atLeast = 1)  { putStudentsService.updateStudentsDateBirth(LocalDate.parse("2024-01-01"), 1L) }
    }
}