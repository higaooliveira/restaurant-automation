package com.higor.restaurantautomation.domain.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [RealDiscountCalculator::class, PercentageDiscountCalculator::class])
class DiscountCalculatorTest {

    @Autowired
    lateinit var realDiscountCalculator: RealDiscountCalculator

    @Autowired
    lateinit var percentageDiscountCalculator: PercentageDiscountCalculator

    @Test
    fun discountCalculators() {
        val price = 1000.00
        val discount = 10.00
        val expectedPercentageResult = 900.00
        val expectedRealResult = 990.00

        assert(percentageDiscountCalculator.calculate(price, discount) == expectedPercentageResult)
        assert(realDiscountCalculator.calculate(price, discount) == expectedRealResult)
    }
}
