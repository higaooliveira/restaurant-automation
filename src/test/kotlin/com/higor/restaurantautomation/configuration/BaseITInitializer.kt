package com.higor.restaurantautomation.configuration

import com.higor.restaurantautomation.configuration.testcontainers.PostgresContainer
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import java.security.Key
import java.security.KeyPairGenerator
import java.util.Base64

class BaseITInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    companion object {

        private val POSTGRES = PostgresContainer.buildContainer()

        const val RSA_HEADER = "-----BEGIN RSA KEY-----\n"
        const val RSA_FOOTER = "\n-----END RSA KEY-----\n"
    }

    override fun initialize(context: ConfigurableApplicationContext) {
        POSTGRES.start()

        val keygen = KeyPairGenerator.getInstance("RSA").apply { initialize(2048) }
        val keys = keygen.generateKeyPair()
        val jwtPrivateKey = toBase64Key(keys.private)
        PostgresContainer.buildProperties(POSTGRES).applyTo(context.environment)

        TestPropertyValues.of(
            "jwt.secret=$jwtPrivateKey",
            "jwt.expiration-time=600000",
        ).applyTo(context.environment)
    }

    private fun toBase64Key(key: Key): String {
        val encoder = Base64.getMimeEncoder()
        return RSA_HEADER + encoder.encodeToString(key.encoded) + RSA_FOOTER
    }
}
