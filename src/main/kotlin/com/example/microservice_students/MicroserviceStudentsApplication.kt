package com.example.microservice_students

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class MicroserviceStudentsApplication

fun main(args: Array<String>) {

    runApplication<MicroserviceStudentsApplication>(*args)
}

