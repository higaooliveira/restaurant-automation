package com.higor.restaurantautomation.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PasswordHelperTest {

    @Test
    fun encodePasswordTest(){
        val password = "teste"

        val returnedString = PasswordHelper.encode(password)

        Assertions.assertNotSame(password, returnedString)
    }
}