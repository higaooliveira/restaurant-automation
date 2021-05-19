package com.higor.restaurantautomation

import com.google.zxing.qrcode.QRCodeWriter
import com.higor.restaurantautomation.adapters.security.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@Import(SecurityConfig::class, QRCodeWriter::class)
@SpringBootApplication

class RestaurantautomationApplication

fun main(args: Array<String>) {
    runApplication<RestaurantautomationApplication>(*args)
}