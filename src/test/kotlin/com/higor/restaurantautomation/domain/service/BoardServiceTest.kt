package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.BoardRepository
import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.utils.QrCodeWriter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import java.util.*

@SpringBootTest
@ContextConfiguration(classes = [BoardService::class])
class BoardServiceTest {

    @MockBean
    lateinit var repository: BoardRepository

    @MockBean
    lateinit var companyService: CompanyService

    @MockBean
    lateinit var qrCodeWriter: QrCodeWriter

    @Autowired
    lateinit var service: BoardService

    @Test
    fun getBoardTest() {
        val company = Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Optional.of(Board(1L, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(1L)).thenReturn(expectedBoard)

        val actualBoard = service.getById(1L)
        Assertions.assertEquals(expectedBoard.get(), actualBoard)
        Assertions.assertEquals(expectedBoard.get().id, actualBoard.id)
    }

    @Test
    fun getAllBoardTest() {
        val company = Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoardList = listOf(Board(1L, 1L, "www.foobar.com.br", company), Board(2L, 2L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findAll()).thenReturn(expectedBoardList)

        val actualBoardList = service.getAll()
        Assertions.assertEquals(expectedBoardList, actualBoardList)
    }

    @Test
    fun createBoardTest() {
        val boardDto = CreateBoardDto(1L, 1L)
        val company = Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Board(1L, 1L, "/tmp/board_1_1.png", company)
        val content = "${expectedBoard.company}_${expectedBoard.id}_${expectedBoard.number}"
        BDDMockito.`when`(companyService.getById(1L)).thenReturn(company)
        BDDMockito.doNothing().`when`(qrCodeWriter).write(expectedBoard.qrCodeLink, content)

        BDDMockito.`when`(repository.save(ArgumentMatchers.any(Board::class.java))).thenReturn(expectedBoard)
        val returnedBoard = service.create(boardDto)

        Assertions.assertEquals(expectedBoard, returnedBoard)
    }

    @Test
    fun deleteBookTest() {
        val company = Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Optional.of(Board(1L, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(1L)).thenReturn(expectedBoard)
        service.delete(expectedBoard.get().id!!)
        BDDMockito.verify(repository, Mockito.times(1)).deleteById(expectedBoard.get().id!!)
    }
}
