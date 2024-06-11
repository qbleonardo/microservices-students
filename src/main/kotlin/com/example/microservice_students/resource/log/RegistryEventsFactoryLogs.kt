package com.example.microservice_students.resource.log

import com.example.microservice_students.resource.log.LogFactory.log
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import io.github.resilience4j.retry.Retry
import lombok.extern.slf4j.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Slf4j
@Configuration
class RegistryEventsFactoryLogs {

    @Bean
    fun circuitBreakerLogsRegistry(): RegistryEventConsumer<CircuitBreaker>{
        return object: RegistryEventConsumer<CircuitBreaker>{
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<CircuitBreaker>) {
                entryAddedEvent.addedEntry.eventPublisher.onStateTransition { event ->
                    log.info("Circuit Breaker: Estado mudou de {} para {}",
                        event.stateTransition.fromState, event.stateTransition.toState)
                }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<CircuitBreaker>) {
                TODO("Not yet implemented")
            }

            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<CircuitBreaker>) {
                TODO("Not yet implemented")
            }

        }
    }

    @Bean
    fun retryLogsRegistry(): RegistryEventConsumer<Retry>{
        return object: RegistryEventConsumer<Retry>{
            override fun onEntryAddedEvent(entryAddedEvent: EntryAddedEvent<Retry>) {
                entryAddedEvent.addedEntry.eventPublisher.onEvent { event ->
                    log.info("Retry: Tentativa de chamada número: [{}]",
                        event.numberOfRetryAttempts)
                }
            }

            override fun onEntryRemovedEvent(entryRemoveEvent: EntryRemovedEvent<Retry>) {
                TODO("Not yet implemented")

            }

            override fun onEntryReplacedEvent(entryReplacedEvent: EntryReplacedEvent<Retry>) {
                TODO("Not yet implemented")
            }

        }
    }
}