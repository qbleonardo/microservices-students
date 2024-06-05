package com.example.microservice_students.application.controller.request

data class GradesRequest(
        var studentName: String,
        var subject: String,
        var gradeValue: Int
)
