package com.example.microservice_students.domain.service

import com.example.microservice_students.domain.exception.StudentsAlreadyCreatedException
import com.example.microservice_students.domain.exception.StudentsNotFoundException
import com.example.microservice_students.fixture.StudentsFixture.getStudentsFixture
import com.example.microservice_students.fixture.StudentsFixture.getStudentsListFixture
import com.example.microservice_students.resource.repository.StudentsRepository
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
import java.util.*

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
    fun shouldToBeEqualsNameSaved() {
        val student = getStudentsFixture()
        every { studentsRepository.existsByNameAndDateBirth(student.name, student.dateBirth) } returns false
        every { studentsRepository.save(student) } returns student

        val createStudent = studentsService.createStudents(student)

        assertEquals(student.name, createStudent.name)
    }

    @Test
    fun shouldThrowStudentsExistsException() {
        every { studentsRepository.existsByNameAndDateBirth(getStudentsFixture().name, getStudentsFixture().dateBirth) } returns true

        assertThrows<StudentsAlreadyCreatedException> { studentsService.createStudents(getStudentsFixture()) }
    }

    @Test
    fun shouldReturnStudentsById(){
        every { studentsRepository.findById(1) } returns Optional.of(getStudentsFixture())

        val studentsById = studentsService.getStudentsById(1)

        assertEquals(studentsRepository.findById(1).get().id, studentsById.id)
    }

    @Test
    fun shouldReturnSamePageSizeChoicedAndStatusOK() {
        val pageImpl = PageImpl(getStudentsListFixture())

        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns pageImpl

        val allStudents = studentsService.getAllStudents(0, 1)

        assertEquals(1, allStudents.size)
    }

    @Test
    fun shouldThrowStudentsNotFoundException_whenNotFoundAllStudents(){
        every { studentsRepository.findAll(PageRequest.of(0,1)) } returns Page.empty()

        assertThrows<StudentsNotFoundException> { studentsService.getAllStudents(0, 1) }
    }

    @Test
    fun shouldUpdateStudentsByDateBirth() {
        val date = LocalDate.parse("2024-01-01")
        val studentId = 1L

        every { studentsRepository.updateByDateBirth(date, studentId) } returns Unit
        every { studentsRepository.findById(studentId) } returns Optional.of(getStudentsFixture())

        studentsService.updateStudentsDateBirth(date, studentId)

        verify(exactly = 1) { studentsRepository.findById(studentId) }
        verify(exactly = 1)  { studentsRepository.updateByDateBirth(date, studentId) }
    }

    @Test
    fun shouldRemoveStudentsById() {
        val studentId = 1L

        every { studentsRepository.findById(studentId) } returns Optional.of(getStudentsFixture())
        every { studentsRepository.deleteById(studentId) } returns Unit

        studentsService.removeStudentsById(studentId)

        verify(exactly = 1) { studentsRepository.findById(studentId) }
        verify(exactly = 1) { studentsRepository.deleteById(studentId) }
    }

    @Test
    fun shouldThrowStudentNotFoundException_whenNotFoundById() {
        every { studentsRepository.findById(1L) } returns Optional.empty()

        assertThrows<StudentsNotFoundException>{ studentsService.removeStudentsById(1L) }
    }


}