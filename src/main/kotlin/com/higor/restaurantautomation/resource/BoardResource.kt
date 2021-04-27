package com.higor.restaurantautomation.resource

import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import com.higor.restaurantautomation.utils.HateoasHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class BoardResource(@Autowired private val boardService: BoardServiceContract) {

    @GetMapping("/board")
    fun getAllBoards(): ResponseEntity<List<Board>> = ResponseEntity.ok(this.boardService.getAll())

    @GetMapping("/board/{id}")
    fun getCompany(@PathVariable id: Long): ResponseEntity<Board> = ResponseEntity.ok(this.boardService.getById(id))

    @PostMapping("/board")
    fun create(@RequestBody createDto: CreateBoardDto): ResponseEntity<Board> {
        val board = this.boardService.create(createDto)

        return ResponseEntity
            .created(HateoasHelper.buildUrlToGetRequest(board.id!!))
            .body(board)
    }

    @DeleteMapping("/board/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Board> {
        this.boardService.delete(id)

        return ResponseEntity.noContent().build()
    }
}