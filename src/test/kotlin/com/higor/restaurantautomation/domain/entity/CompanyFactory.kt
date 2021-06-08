package com.higor.restaurantautomation.domain.entity

import java.util.UUID

fun createCompany(
    id: UUID = UUID.randomUUID(),
    name: String = "John Doe",
    email: String = "johndoe@mock.com",
    password: String = "123456",
    phone: String = "123456",
    document: String = "123456"
) = Company(
    id = id,
    name = name,
    email = email,
    password = password,
    phone = phone,
    document = document
)
