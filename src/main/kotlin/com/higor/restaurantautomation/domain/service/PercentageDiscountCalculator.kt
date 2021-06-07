package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.service.contracts.DiscountCalculatorStrategy

object PercentageDiscountCalculator : DiscountCalculatorStrategy {
    private const val DIVIDER = 100

    override fun calculate(discount: Double, price: Double): Double {
        return price * (discount / DIVIDER)
    }
}
