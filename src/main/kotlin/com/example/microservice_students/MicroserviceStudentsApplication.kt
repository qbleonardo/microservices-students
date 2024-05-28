package com.example.microservice_students

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicroserviceStudentsApplication

fun main(args: Array<String>) {

    runApplication<MicroserviceStudentsApplication>(*args)
}

