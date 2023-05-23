package com.higor.restaurantautomation.configuration

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource

@EnableAutoConfiguration
@SpringBootConfiguration
@EnableConfigurationProperties
@ComponentScan(
    basePackages = [
        "com.higor.restaurantautomation",
    ],
)
@PropertySource(value = ["classpath:application-test.yml"], ignoreResourceNotFound = true)
class BaseITConfiguration
