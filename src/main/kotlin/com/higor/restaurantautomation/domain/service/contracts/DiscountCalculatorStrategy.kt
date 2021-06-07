package com.higor.restaurantautomation.domain.service.contracts

interface DiscountCalculatorStrategy {
    fun calculate(discount: Double, price: Double): Double
}
