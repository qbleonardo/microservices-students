package com.example.microservice_students.service

import com.example.microservice_students.repository.StudentsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeleteStudentsServiceTest {

    @MockK
    lateinit var studentsRepository: StudentsRepository

    @InjectMockKs
    lateinit var studentsDeleteStudentsService: DeleteStudentsService

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }
    @Test
    fun shouldRemoveStudentsById() {
        every { studentsRepository.deleteById(1L) } returns Unit

        studentsDeleteStudentsService.removeStudentsById(1L)

        verify(atLeast = 1) { studentsRepository.deleteById(1L) }
        verify(atLeast = 1) { studentsDeleteStudentsService.removeStudentsById(1L) }
    }
}