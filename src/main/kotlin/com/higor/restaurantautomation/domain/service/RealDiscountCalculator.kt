package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.service.contracts.DiscountCalculatorStrategy

object RealDiscountCalculator : DiscountCalculatorStrategy {
    override fun calculate(discount: Double, price: Double): Double = price - discount
}
