package com.example.demo.config

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DbCleanupService(
    private val entityManager: EntityManager
) {
    @Transactional
    fun truncate() {
        val tableNames =
            entityManager.createNativeQuery("""SELECT STRING_AGG(CONCAT('"', TABLE_NAME, '"'), ',') FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'demo'""")
                .singleResult

        entityManager
            .createNativeQuery("SET SCHEMA 'demo'; TRUNCATE TABLE $tableNames CASCADE;")
            .executeUpdate()
    }
}