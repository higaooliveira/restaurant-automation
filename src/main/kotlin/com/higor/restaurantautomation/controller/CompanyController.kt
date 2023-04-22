// package com.higor.restaurantautomation.controller
//
// import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
// import com.higor.restaurantautomation.domain.dto.UpdateCompanyDto
// import com.higor.restaurantautomation.domain.dto.UpdateCompanyPasswordDto
// import com.higor.restaurantautomation.adapters.entity.Company
// import com.higor.restaurantautomation.domain.service.CompanyService
// import org.springframework.beans.factory.annotation.Autowired
// import org.springframework.http.HttpStatus
// import org.springframework.http.ResponseEntity
// import org.springframework.web.bind.annotation.DeleteMapping
// import org.springframework.web.bind.annotation.GetMapping
// import org.springframework.web.bind.annotation.PatchMapping
// import org.springframework.web.bind.annotation.PostMapping
// import org.springframework.web.bind.annotation.PutMapping
// import org.springframework.web.bind.annotation.RequestAttribute
// import org.springframework.web.bind.annotation.RequestBody
// import org.springframework.web.bind.annotation.RequestMapping
// import org.springframework.web.bind.annotation.RestController
// import java.util.UUID
//
// @RestController
// @RequestMapping("/api")
// class CompanyController(@Autowired private val companyService: CompanyService) {
//
//     @GetMapping("/office/company")
//     fun getCompany(@RequestAttribute("companyId") id: UUID): ResponseEntity<Company> {
//         val company = this.companyService.getById(id)
//
//         return ResponseEntity.ok(company)
//     }
//
//     @PostMapping("/office/company")
//     fun create(@RequestBody createDto: CreateCompanyDto): ResponseEntity<Company> {
//         val company = this.companyService.create(createDto)
//
//         return ResponseEntity
//             .status(HttpStatus.CREATED)
//             .body(company)
//     }
//
//     @PutMapping("/office/company")
//     fun update(
//         @RequestBody updateDto: UpdateCompanyDto,
//         @RequestAttribute("companyId") id: UUID
//     ): ResponseEntity<Company> {
//         updateDto.id = id
//         val company = this.companyService.update(updateDto)
//
//         return ResponseEntity
//             .status(HttpStatus.OK)
//             .body(company)
//     }
//
//     @PatchMapping("/office/company/password")
//     fun updatePassword(
//         @RequestBody updateDto: UpdateCompanyPasswordDto,
//         @RequestAttribute("companyId") id: UUID
//     ): ResponseEntity<Company> {
//         updateDto.id = id
//         val company = this.companyService.updatePassword(updateDto)
//
//         return ResponseEntity.ok(company)
//     }
//
//     @DeleteMapping("/office/company")
//     fun delete(@RequestAttribute("companyId") id: UUID): ResponseEntity<Company> {
//         this.companyService.delete(id)
//
//         return ResponseEntity.noContent().build()
//     }
// }
