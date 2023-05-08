package com.higor.restaurantautomation.utils.factories

import com.github.javafaker.Faker
import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.utils.extensions.toUUID
import java.util.UUID

object Factory {
    private val faker = Faker()

    val invalidId: UUID by lazy {
        UUID.randomUUID()
    }

    val invalidDocument: String by lazy {
        "0000000000"
    }

    val invalidEmail: String by lazy {
        faker.internet().emailAddress()
    }

    val companyEntity: Company by lazy {
        Company(
            id = "bd2481ba-5558-4317-a808-869f6c6731ec".toUUID(),
            socialName = faker.company().name(),
            fantasyName = faker.company().name(),
            document = "01001000",
            phone = faker.phoneNumber().cellPhone(),
            createdAt = faker.date().birthday().toInstant(),
        )
    }

    val userEntity: User by lazy {
        User(
            id = "23b75d42-05a8-4a23-bc7b-4baf35f60f5a".toUUID(),
            name = faker.name().fullName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(),
            role = Role.ADMIN,
            company = companyEntity,
        )
    }
}
