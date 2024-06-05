package com.example.microservice_students.domain.service

import com.example.microservice_students.domain.model.Student
import com.example.microservice_students.domain.repository.StudentsRepository
import com.example.microservice_students.domain.exception.StudentsExistsException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.fixture.StudentsFixture.getStudentsFixture
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

class StudentsServiceTest {

    @MockK
    lateinit var studentsRepository: StudentsRepository

    @InjectMockKs
    lateinit var studentsService: StudentsService

    @BeforeEach
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun givenStudentName_whenCreated_shouldToBeEqualsNameSaved() {
        every { studentsRepository.existsByNameAndDateBirth("Leo", LocalDate.parse("2020-10-16")) } returns false

        every { studentsRepository.save(getStudentsFixture()) } returns getStudentsFixture()

        val studentCreated = studentsRepository.save(getStudentsFixture())

        assertEquals(studentCreated.name, studentsService.createStudents(getStudentsFixture()).name)
    }

    @Test
    fun shouldThrowStudentsFoundException() {
        every { studentsRepository.existsByNameAndDateBirth("Leo", LocalDate.parse("2020-10-16")) } returns true

        assertThrows<StudentsExistsException> { studentsService.createStudents(getStudentsFixture()) }
    }

    @Test
    fun shouldReturnSameSizeChoiceAndStatusOK() {
        val studentList = listOf(Student(2, "A", LocalDate.parse("2020-01-02")))

        val pageImpl = PageImpl(studentList)

        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns pageImpl

        val allStudents = studentsService.getAllStudents(0, 1)

        assertEquals(1, allStudents.size)
    }

    @Test
    fun shouldThrowStudentsNotFoundException(){
        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns Page.empty()

        assertThrows<StudentsNotFoundException> { studentsService.getAllStudents(0, 1) }
    }

    @Test
    fun shouldUpdateStudentsByDateBirth() {
        every { studentsRepository.updateByDateBirth(LocalDate.parse("2024-01-01"), 1L) } returns Unit

        studentsService.updateStudentsDateBirth(LocalDate.parse("2024-01-01"), 1L)

        verify(atLeast = 1)  { studentsRepository.updateByDateBirth(LocalDate.parse("2024-01-01"), 1L) }
        verify(atLeast = 1)  { studentsService.updateStudentsDateBirth(LocalDate.parse("2024-01-01"), 1L) }
    }

    @Test
    fun shouldRemoveStudentsById() {
        every { studentsRepository.deleteById(1L) } returns Unit

        studentsService.removeStudentsById(1L)

        verify(atLeast = 1) { studentsRepository.deleteById(1L) }
        verify(atLeast = 1) { studentsService.removeStudentsById(1L) }
    }


}