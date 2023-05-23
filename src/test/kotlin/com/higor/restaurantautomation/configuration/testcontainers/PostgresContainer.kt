package com.higor.restaurantautomation.configuration.testcontainers

import org.springframework.boot.test.util.TestPropertyValues
import org.testcontainers.containers.PostgreSQLContainer

class PostgresContainer {

    companion object {
        private const val username = "restaurant-automation"
        private const val password = "root"
        fun buildContainer(): PostgreSQLContainer<out PostgreSQLContainer<*>> {
            return PostgreSQLContainer<Nothing>("postgres:11.1").apply {
                withDatabaseName(Companion.username)
                withUsername(Companion.username)
                withPassword(Companion.password)
                withInitScript("init-postgres.sql")
                setCommand("postgres -c max_connections=1000")
            }
        }

        fun buildProperties(instance: PostgreSQLContainer<out PostgreSQLContainer<*>>): TestPropertyValues {
            println(instance.getJdbcUrl())
            return TestPropertyValues.of(
                "spring.datasource.url=${instance.jdbcUrl}",
                "spring.datasource.username=$username",
                "spring.datasource.password=$password",
            )
        }
    }
}
