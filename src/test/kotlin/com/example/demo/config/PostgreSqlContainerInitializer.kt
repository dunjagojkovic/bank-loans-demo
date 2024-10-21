package com.example.demo.config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.Environment
import org.springframework.core.env.MapPropertySource
import org.springframework.core.env.PropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import java.time.Duration

class PostgreSqlContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        val environment: Environment = applicationContext.environment
        val imageName: String = environment.getProperty("testcontainer.postgresql.image")!!

        val postgres = PostgreSQLContainer(DockerImageName.parse(imageName))
        postgres.withStartupTimeout(Duration.ofSeconds(60L))
            .withDatabaseName("testDB")
            .withUsername("test")
            .withPassword("test")
            .start()

        val envMap = mapOf(
            "spring.datasource.url" to postgres.jdbcUrl,
            "spring.datasource.username" to postgres.username,
            "spring.datasource.password" to postgres.password
        )
        val propertySource: PropertySource<*> = MapPropertySource("testcontainers", envMap)
        applicationContext.environment.propertySources.addFirst(propertySource)
    }
}