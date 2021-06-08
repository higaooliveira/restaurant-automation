package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.BoardResponse
import com.higor.restaurantautomation.domain.dto.CreateBoard
import com.higor.restaurantautomation.domain.dto.PagedBoardsResponse
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api")
class BoardResource(
    @Autowired private val boardService: BoardServiceContract
) {

    @GetMapping("/board")
    fun getAllBoards(
        @RequestAttribute("companyId") companyId: UUID,
        pageable: Pageable
    ): ResponseEntity<PagedBoardsResponse> {
        val pagedBoardsResponse = this.boardService.getAll(companyId, pageable)

        return ResponseEntity.ok(pagedBoardsResponse)
    }

    @GetMapping("/board/{id}")
    fun getBoard(@PathVariable id: UUID): ResponseEntity<BoardResponse> {
        val board = this.boardService.getById(id)
        return ResponseEntity.ok(board.toBoardResponse())
    }

    @PostMapping("/board")
    fun create(
        @RequestBody createDto: CreateBoard,
        @RequestAttribute("companyId") companyId: UUID
    ): ResponseEntity<BoardResponse> {
        createDto.companyId = companyId
        val board = this.boardService.create(createDto)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(board)
    }

    @DeleteMapping("/board/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<BoardResponse> {
        this.boardService.delete(id)

        return ResponseEntity.noContent().build()
    }
}
