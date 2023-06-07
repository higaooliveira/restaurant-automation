package com.higor.restaurantautomation.domain.service.auth.impl

import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.configuration.security.UserDetailsImpl
import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto
import com.higor.restaurantautomation.domain.model.company.CompanyModel
import com.higor.restaurantautomation.domain.model.user.UserModel
import com.higor.restaurantautomation.domain.service.auth.RegisterService
import com.higor.restaurantautomation.domain.service.company.CreateCompanyService
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterServiceImpl(
    private val createCompanyService: CreateCompanyService,
    private val createUserService: CreateUserService,
    private val getUserByIdService: GetUserByIdService,
    private val jwtUtil: JWTUtil,
) : RegisterService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun execute(request: RegisterDto): AuthenticationDtoOut {
        val companyModel = CompanyModel.from(request)

        val companyId = createCompanyService.execute(companyModel)

        val userModel = UserModel.from(request, companyId)

        val userId = createUserService.execute(userModel)

        val user = getUserByIdService.execute(userId)

        val accessToken = jwtUtil.generateToken(UserDetailsImpl(user))

        return AuthenticationDtoOut(
            accessToken = accessToken,
        )
    }
}
