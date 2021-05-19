package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.CompanyResponse
import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.utils.HateoasHelper
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
class CompanyResource(@Autowired private val companyService: CompanyServiceContract) {

    @GetMapping("/company")
    fun getCompany(@RequestAttribute("companyId") id: UUID): ResponseEntity<CompanyResponse> {
        val company = this.companyService.getById(id)
        val response = MapperUtils.convert<Company, CompanyResponse>(company)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/company")
    fun create(@RequestBody createDto: CreateCompanyDto): ResponseEntity<CompanyResponse> {
        val company = this.companyService.create(createDto)
        val response = MapperUtils.convert<Company, CompanyResponse>(company)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }

    @PutMapping("/company")
    fun update(
            @RequestBody updateDto: UpdateCompanyDto,
            @RequestAttribute("companyId") id: UUID
    ): ResponseEntity<CompanyResponse> {
        val company = this.companyService.update(updateDto)
        val response = MapperUtils.convert<Company, CompanyResponse>(company)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(response)
    }

    @PatchMapping("/company/password")
    fun updatePassword(@RequestBody updateDto: UpdateCompanyPasswordDto): ResponseEntity<CompanyResponse> {
        val company = this.companyService.updatePassword(updateDto)
        val response = MapperUtils.convert<Company, CompanyResponse>(company)

        return ResponseEntity
            .status(HttpStatus.OK).location(HateoasHelper.buildUrlToGetRequest(company.id))
            .body(response)
    }

    @DeleteMapping("/company/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<CompanyResponse> {
        this.companyService.delete(id)

        return ResponseEntity.noContent().build()
    }
}
