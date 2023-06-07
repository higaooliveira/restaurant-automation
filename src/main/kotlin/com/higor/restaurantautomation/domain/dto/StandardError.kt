package com.higor.restaurantautomation.domain.dto

data class StandardError(
    val code: String,
    val message: String,
    val details: String? = null,
    val errorFields: Set<String>? = null,
) {
    companion object
}
