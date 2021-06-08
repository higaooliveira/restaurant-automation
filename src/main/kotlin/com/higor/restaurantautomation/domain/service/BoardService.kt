package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.BoardRepository
import com.higor.restaurantautomation.domain.dto.BoardResponse
import com.higor.restaurantautomation.domain.dto.CreateBoard
import com.higor.restaurantautomation.domain.dto.PagedBoardsResponse
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.MapperUtils
import com.higor.restaurantautomation.utils.QrCodeWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardService(
    @Autowired private val boardRepository: BoardRepository,
    @Autowired private val companyService: CompanyService,
    @Autowired private val qrCodeWriter: QrCodeWriter,
) : BoardServiceContract {

    override fun getById(id: UUID): Board = this.boardRepository
        .findById(id)
        .orElseThrow { ResourceNotFound("Resource Not Found for passed id") }

    override fun getAll(companyId: UUID, pageable: Pageable): PagedBoardsResponse {
        val pagedBoards = this.boardRepository.findAllByCompanyId(companyId, pageable)

        return PagedBoardsResponse(
            boards = pagedBoards.toList().map { it.toBoardResponse() },
            page = pagedBoards.pageable.pageNumber,
            size = pagedBoards.toList().size,
            totalPages = pagedBoards.totalPages,
            lastPage = pagedBoards.isLast
        )
    }

    override fun create(createDto: CreateBoard): BoardResponse {
        val company = this.companyService.getById(createDto.companyId!!)
        if (this.boardExists(createDto.number, company)) {
            throw ResourceAlreadyExists("Resource already exists")
        }

        var board = MapperUtils.convert<CreateBoard, Board>(createDto)
        board.company = company

        board.qrCodeLink = this.getQRCodeLink(board)

        board = this.boardRepository.save(board)

        this.generateQrCode(board)
        return board.toBoardResponse()
    }

    override fun delete(id: UUID) {
        try {
            this.boardRepository.deleteById(id)
        } catch (ex: EmptyResultDataAccessException) {
            throw ResourceNotFound("Resource Not Found for passed id")
        }
    }

    private fun generateQrCode(board: Board) {
        val content = "${board.company}_${board.id}_${board.number}"
        qrCodeWriter.write(board.qrCodeLink, content)
    }

    private fun boardExists(number: Long, company: Company): Boolean =
        this.boardRepository.findByNumberAndCompany(number, company) != null

    private fun getQRCodeLink(board: Board): String = QrCodeWriter.PATH
        .plus("board_".plus(board.number).plus("_").plus(board.company.id))
        .plus(QrCodeWriter.EXTENSION)
}
