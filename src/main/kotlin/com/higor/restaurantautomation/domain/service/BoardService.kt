package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.dto.CreateCompanyDto
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.repository.BoardRepository
import com.higor.restaurantautomation.domain.respository.CompanyRepository
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import com.higor.restaurantautomation.domain.service.contracts.CompanyServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class BoardService(@Autowired val boardRepository: BoardRepository,
                   @Autowired val companyService: CompanyService): BoardServiceContract {

    override fun getById(id: Long): Board = this.boardRepository
            .findById(id)
            .orElseThrow { ResourceNotFound("Resource Not Found for passed id") }

    override fun getAll(): List<Board> = this.boardRepository
            .findAll()

    override fun create(createDto: CreateBoardDto): Board {
        val company = this.companyService.getById(createDto.companyId)
        if (this.boardExists(createDto.number, company)){
            throw ResourceAlreadyExists("Resource already exists")
        }
        val board = Board(number = createDto.number, company = company)

        return this.boardRepository.save(board)
    }

    override fun delete(id: Long) {
        try {
            this.boardRepository.deleteById(id)
        }catch (ex: EmptyResultDataAccessException){
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }

    private fun boardExists(number: Long, company: Company): Boolean = this.boardRepository.findByNumberAndCompany(number, company) != null
}