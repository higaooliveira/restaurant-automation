package com.higor.restaurantautomation.domain.service.impl

import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.RegisterDto
import com.higor.restaurantautomation.domain.service.CompanyExistsService
import com.higor.restaurantautomation.domain.service.CompanyRegisterService
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CompanyRegisterServiceImpl(
    private val companyExistsService: CompanyExistsService,
    private val companyRepository: CompanyRepository,
    private val jwtUtil: JWTUtil,
) : CompanyRegisterService {
    override fun execute(request: RegisterDto): AuthenticationDtoOut {
        val companyModel = request.toModel()

        if (companyExistsService.execute(companyModel.email, companyModel.document)) {
            throw ApiException("Company Already Exists", statusCode = HttpStatus.CONFLICT)
        }

        val company = companyRepository.save(companyModel.toEntity())

        val jwtToken = jwtUtil.generateToken(company)

        return AuthenticationDtoOut(
            accessToken = jwtToken,
        )
    }
}
