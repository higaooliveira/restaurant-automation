package com.higor.restaurantautomation.domain.service.contracts

interface DiscountCalculatorStrategy {
    fun calculate(price: Double, discount: Double): Double
}
