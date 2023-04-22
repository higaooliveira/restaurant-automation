package com.higor.restaurantautomation.utils.validators

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailValidator : ConstraintValidator<Email, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return false
        }

        val pattern: Pattern = Pattern.compile("^[a-zA-Z0-9_!#\$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$")
        val matcher: Matcher = pattern.matcher(value)

        return matcher.matches()
    }
}
