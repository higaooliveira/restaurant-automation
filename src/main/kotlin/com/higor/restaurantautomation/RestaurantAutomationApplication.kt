package com.higor.restaurantautomation

import com.google.zxing.qrcode.QRCodeWriter
import com.higor.restaurantautomation.configuration.security.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(SecurityConfig::class, QRCodeWriter::class)
@SpringBootApplication

class RestaurantAutomationApplication

fun main(args: Array<String>) {
    runApplication<RestaurantAutomationApplication>(*args)
}
