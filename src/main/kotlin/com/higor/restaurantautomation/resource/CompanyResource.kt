package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.DTO.CompanyDTO
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.CompanyService
import com.higor.restaurantautomation.utils.HateoasHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class CompanyResource (@Autowired val companyService: CompanyService){

    @GetMapping("/company/{id}")
    fun getCompany(@PathVariable id: Long) : ResponseEntity<Company> {
        val company = this.companyService.getCompany(id)
        return ResponseEntity.ok(company)
    }

    @PostMapping("/company")
    fun create(@RequestBody companyDTO: CompanyDTO): ResponseEntity<Company> {
        val company = this.companyService.create(companyDTO)

        return ResponseEntity.created(HateoasHelper.buildUrlToGetRequest(company.id!!)).body(company)
    }
}