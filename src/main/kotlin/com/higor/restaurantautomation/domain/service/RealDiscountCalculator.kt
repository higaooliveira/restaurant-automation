package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.service.contracts.DiscountCalculatorStrategy
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
@Service
object RealDiscountCalculator : DiscountCalculatorStrategy {
    override fun calculate(price: Double, discount: Double): Double {
        return price - discount
    }
}
