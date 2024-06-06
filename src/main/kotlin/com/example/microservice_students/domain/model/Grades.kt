package com.example.microservice_students.domain.model

data class Grades(
        var id: Long,
        var studentName: String,
        var subject: String,
        var gradeValue: Int
)
