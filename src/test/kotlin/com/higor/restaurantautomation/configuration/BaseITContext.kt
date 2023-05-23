package com.higor.restaurantautomation.configuration

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@Configuration
@ActiveProfiles("test")
@ContextConfiguration(
    classes = [BaseITConfiguration::class],
    initializers = [BaseITInitializer::class]
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class BaseITContext

