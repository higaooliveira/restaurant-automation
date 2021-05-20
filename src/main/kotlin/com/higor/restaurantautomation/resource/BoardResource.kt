package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import org.springframework.beans.factory.annotation.Autowired
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
class BoardResource(@Autowired private val boardService: BoardServiceContract) {

    @GetMapping("/board")
    fun getAllBoards(@RequestAttribute("companyId") companyId: UUID): ResponseEntity<List<Board>> =
        ResponseEntity.ok(this.boardService.getAll(companyId))

    @GetMapping("/board/{id}")
    fun getBoard(@PathVariable id: UUID): ResponseEntity<Board> = ResponseEntity.ok(this.boardService.getById(id))

    @PostMapping("/board")
    fun create(
        @RequestBody createDto: CreateBoardDto,
        @RequestAttribute("companyId") companyId: UUID
    ): ResponseEntity<Board> {
        createDto.companyId = companyId
        val board = this.boardService.create(createDto)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(board)
    }

    @DeleteMapping("/board/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Board> {
        this.boardService.delete(id)

        return ResponseEntity.noContent().build()
    }
}
