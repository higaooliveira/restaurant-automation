package com.higor.restaurantautomation.controller

import com.higor.restaurantautomation.domain.dto.CompanyDtoOut
import com.higor.restaurantautomation.domain.dto.UpdateCompanyDtoIn
import com.higor.restaurantautomation.domain.service.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.UpdateCompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/office/company")
class CompanyController(
    private val getCompanyByIdService: GetCompanyByIdService,
    private val updateCompanyService: UpdateCompanyService,
) {

    @GetMapping()
    fun getCompany(@RequestAttribute("companyId") id: UUID): ResponseEntity<CompanyDtoOut> {
        val company = getCompanyByIdService.execute(id)

        return ResponseEntity.ok(company.toDto())
    }

    @PutMapping()
    fun update(
        @RequestBody updateDto: UpdateCompanyDtoIn,
    ): ResponseEntity<CompanyDtoOut> {
        val company = updateCompanyService.execute(updateDto)

        return ResponseEntity
            .ok(company.toDto())
    }
    //
    // @PatchMapping("/office/company/password")
    // fun updatePassword(
    //     @RequestBody updateDto: UpdateCompanyPasswordDto,
    //     @RequestAttribute("companyId") id: UUID,
    // ): ResponseEntity<Company> {
    //     updateDto.id = id
    //     val company = this.companyService.updatePassword(updateDto)
    //
    //     return ResponseEntity.ok(company)
    // }
    //
    // @DeleteMapping("/office/company")
    // fun delete(@RequestAttribute("companyId") id: UUID): ResponseEntity<Company> {
    //     this.companyService.delete(id)
    //
    //     return ResponseEntity.noContent().build()
    // }
}
