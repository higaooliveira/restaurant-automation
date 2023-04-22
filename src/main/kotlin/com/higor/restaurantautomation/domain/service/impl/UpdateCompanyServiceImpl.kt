package com.higor.restaurantautomation.domain.service.impl

import com.higor.restaurantautomation.adapters.entity.Company
import com.higor.restaurantautomation.adapters.repository.CompanyRepository
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDtoIn
import com.higor.restaurantautomation.domain.model.CompanyModel
import com.higor.restaurantautomation.domain.service.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.UpdateCompanyService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UpdateCompanyServiceImpl(
    private val getCompanyByIdService: GetCompanyByIdService,
    private val companyRepository: CompanyRepository,
) : UpdateCompanyService {
    override fun execute(request: UpdateCompanyDtoIn): CompanyModel {
        val company = getCompanyByIdService.execute(request.id)
        val companyToSave = createCompanyToUpdate(request, company)
        companyRepository.save(companyToSave)
        return CompanyModel.fromEntity(companyToSave)
    }

    private fun createCompanyToUpdate(
        request: UpdateCompanyDtoIn,
        company: CompanyModel,
    ): Company {
        return Company(
            id = request.id,
            name = request.name ?: company.name,
            email = request.email ?: company.email,
            document = request.document ?: company.document,
            phone = request.phone ?: company.phone,
            pass = company.password,
            role = company.role,
            createdAt = company.createdAt,
            updatedAt = Instant.now(),
        )
    }
}
