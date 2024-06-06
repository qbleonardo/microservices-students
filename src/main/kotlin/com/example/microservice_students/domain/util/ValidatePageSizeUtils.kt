package com.example.microservice_students.domain.util

object ValidatePageSizeUtils {

    fun Int.validatePageSize(): Int {
        return if (this < 1) 1 else this
    }
}