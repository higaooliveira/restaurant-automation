package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.utils.HateoasHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
class CompanyResource(@Autowired private val companyService: CompanyServiceContract) {

    @GetMapping("/company")
    fun getCompany(@RequestAttribute("companyId") id: UUID): ResponseEntity<Company> {
        val company = this.companyService.getById(id)
        return ResponseEntity.ok(company)
    }

    @PostMapping("/company")
    fun create(@RequestBody createDto: CreateCompanyDto): ResponseEntity<Company> {
        val company = this.companyService.create(createDto)

        return ResponseEntity
            .created(HateoasHelper.buildUrlToGetRequest(company.id))
            .body(company)
    }

    @PutMapping("/company")
    fun update(@RequestBody updateDto: UpdateCompanyDto, @RequestAttribute("companyId") id: UUID): ResponseEntity<Company> {
        val company = this.companyService.update(updateDto)

        return ResponseEntity
            .status(HttpStatus.OK).location(HateoasHelper.buildUrlToGetRequest(company.id))
            .body(company)
    }

    @PatchMapping("/company/password")
    fun updatePassword(@RequestBody updateDto: UpdateCompanyPasswordDto): ResponseEntity<Company> {
        val company = this.companyService.updatePassword(updateDto)

        return ResponseEntity
            .status(HttpStatus.OK).location(HateoasHelper.buildUrlToGetRequest(company.id))
            .body(company)
    }

    @DeleteMapping("/company/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Company> {
        this.companyService.delete(id)

        return ResponseEntity.noContent().build()
    }
}
