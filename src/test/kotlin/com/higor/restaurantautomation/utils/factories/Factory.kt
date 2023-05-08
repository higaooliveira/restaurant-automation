package com.higor.restaurantautomation.utils.factories

import com.github.javafaker.Faker
import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.RegisterDto
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.model.UserModel
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

    val existentUser: UserModel by lazy {
        UserModel(
            name = userEntity.name,
            email = invalidEmail,
            password = userEntity.password,
            role = Role.ADMIN,
            companyId = userEntity.company.id!!,
            createdAt = userEntity.createdAt,
        )
    }

    val userModel: UserModel by lazy {
        UserModel(
            name = userEntity.name,
            email = userEntity.email,
            password = userEntity.password,
            role = Role.ADMIN,
            companyId = userEntity.company.id!!,
            createdAt = userEntity.createdAt,
        )
    }

    val invalidCompanyModel: CompanyModel by lazy {
        CompanyModel(
            socialName = companyEntity.socialName,
            fantasyName = companyEntity.fantasyName,
            phone = companyEntity.phone,
            document = invalidDocument,
        )
    }

    val companyModel: CompanyModel by lazy {
        CompanyModel(
            socialName = companyEntity.socialName,
            fantasyName = companyEntity.fantasyName,
            phone = companyEntity.phone,
            document = companyEntity.document,
        )
    }

    val registerDto: RegisterDto by lazy {
        RegisterDto(
            socialName = companyEntity.socialName,
            fantasyName = companyEntity.fantasyName,
            phone = companyEntity.phone,
            document = companyEntity.document,
            userEmail = userEntity.email,
            userName = userEntity.name,
            password = userEntity.password,
        )
    }

    val authenticationDtoIn: AuthenticationDtoIn by lazy {
        AuthenticationDtoIn(
            email = userEntity.email,
            password = userEntity.password,
        )
    }
}
