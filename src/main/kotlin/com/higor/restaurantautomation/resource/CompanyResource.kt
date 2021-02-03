package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.CompanyService
import com.higor.restaurantautomation.utils.HateoasHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class CompanyResource (@Autowired val companyService: CompanyService){

    @GetMapping("/company")
    fun getAllCompany() : ResponseEntity<List<Company>> {
        val companies = this.companyService.getAllCompanies()

        return ResponseEntity.ok(companies)
    }
    @GetMapping("/company/{id}")
    fun getCompany(@PathVariable id: Long) : ResponseEntity<Company> {
        val company = this.companyService.getCompany(id)
        return ResponseEntity.ok(company)
    }

    @PostMapping("/company")
    fun create(@RequestBody createCompanyDto: CreateCompanyDto): ResponseEntity<Company> {
        val company = this.companyService.create(createCompanyDto)

        return ResponseEntity
                .created(HateoasHelper.buildUrlToGetRequest(company.id!!))
                .body(company)
    }

    @PutMapping("/company")
    fun update(@RequestBody updateCompanyDto: UpdateCompanyDto): ResponseEntity<Company> {
        val company = this.companyService.update(updateCompanyDto)

        return ResponseEntity
                .status(HttpStatus.OK).location(HateoasHelper.buildUrlToGetRequest(company.id!!))
                .body(company)
    }

    @PatchMapping("/company/password")
    fun updatePassword(@RequestBody updateCompanyPasswordDto: UpdateCompanyPasswordDto): ResponseEntity<Company> {
        val company = this.companyService.updatePassword(updateCompanyPasswordDto)

        return ResponseEntity
                .status(HttpStatus.OK).location(HateoasHelper.buildUrlToGetRequest(company.id!!))
                .body(company)
    }

    @DeleteMapping("/company/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Company> {
        this.companyService.delete(id)

        return ResponseEntity.noContent().build()
    }
}