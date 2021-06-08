package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.service.contracts.DiscountCalculatorStrategy
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
object PercentageDiscountCalculator : DiscountCalculatorStrategy {
    private const val DIVIDER = 100

    override fun calculate(price: Double, discount: Double): Double {
        return price - (price * (discount / DIVIDER))
    }
}
