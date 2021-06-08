package com.higor.restaurantautomation.domain.entity

import java.util.UUID

fun createProduct(
    id: UUID = UUID.randomUUID(),
    name: String = "Teste Mock",
    price: Double = 100.00,
    quantity: Int = 50,
    description: String = "Teste descrição",
    company: Company = createCompany()
) = Product(
    id = id,
    name = name,
    price = price,
    quantity = quantity,
    description = description,
    company = company
)
