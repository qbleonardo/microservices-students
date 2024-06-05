package com.example.microservice_students.application.controller.response

data class GradesResponse(
        var id: Long,
        var studentName: String,
        var subject: String,
        var gradeValue: Int
)
