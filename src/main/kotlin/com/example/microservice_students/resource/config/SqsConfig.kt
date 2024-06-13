package com.example.microservice_students.resource.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI

@Configuration
class SqsConfig {

    @Bean
    fun sqsAsyncClient(): SqsAsyncClient {
        return SqsAsyncClient
            .builder()
            .region(Region.US_EAST_1)
            .endpointOverride(URI.create("http://localhost:4566"))
            .build()
    }
}