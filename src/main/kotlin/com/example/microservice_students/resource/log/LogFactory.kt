package com.example.microservice_students.resource.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LogFactory {

    val log: Logger = LoggerFactory.getLogger(this::class.simpleName)

}