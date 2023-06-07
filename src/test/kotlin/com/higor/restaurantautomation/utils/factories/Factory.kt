package com.higor.restaurantautomation.utils.factories

import com.github.javafaker.Faker
import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.entity.User
import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto
import com.higor.restaurantautomation.domain.dto.user.UserDtoIn
import com.higor.restaurantautomation.domain.model.company.CompanyModel
import com.higor.restaurantautomation.domain.model.user.Role
import com.higor.restaurantautomation.domain.model.user.UserModel
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
            createdAt = faker.date().birthday().toInstant(),
        )
    }

    val userEntity: User by lazy {
        User(
            id = "23b75d42-05a8-4a23-bc7b-4baf35f60f5a".toUUID(),
            name = faker.name().fullName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(),
            phone = faker.phoneNumber().cellPhone(),
            role = Role.ADMIN,
            company = companyEntity,
        )
    }

    val user: User by lazy {
        User(
            id = "23b75d42-05a8-4a23-bc7b-4baf35f60f52".toUUID(),
            name = faker.name().fullName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(),
            phone = faker.phoneNumber().cellPhone(),
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
            phone = faker.phoneNumber().cellPhone(),
            createdAt = userEntity.createdAt,
        )
    }

    val userModel: UserModel by lazy {
        UserModel(
            name = userEntity.name,
            email = userEntity.email,
            password = userEntity.password,
            role = Role.ADMIN,
            phone = faker.phoneNumber().cellPhone(),
            companyId = userEntity.company.id!!,
            createdAt = userEntity.createdAt,
        )
    }

    val invalidCompanyModel: CompanyModel by lazy {
        CompanyModel(
            socialName = companyEntity.socialName,
            fantasyName = companyEntity.fantasyName,
            document = invalidDocument,
        )
    }

    val registerDtoUT: RegisterDto by lazy {
        RegisterDto(
            socialName = companyEntity.socialName,
            fantasyName = companyEntity.fantasyName,
            phone = userEntity.phone,
            document = companyEntity.document,
            userEmail = userEntity.email,
            userName = userEntity.name,
            password = userEntity.password,
        )
    }

    val registerDtoIT: RegisterDto by lazy {
        RegisterDto(
            socialName = faker.company().name(),
            fantasyName = faker.company().name(),
            phone = faker.phoneNumber().cellPhone(),
            document = "010010001",
            userEmail = faker.internet().emailAddress(),
            userName = faker.name().fullName(),
            password = faker.internet().password(),
        )
    }

    val authenticationDtoIn: AuthenticationDtoIn by lazy {
        AuthenticationDtoIn(
            email = userEntity.email,
            password = userEntity.password,
        )
    }

    val userDtoIn: UserDtoIn by lazy {
        UserDtoIn(
            name = faker.name().fullName(),
            companyId = UUID.randomUUID(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(),
            role = Role.USER,
            phone = faker.phoneNumber().cellPhone(),
        )
    }
}
