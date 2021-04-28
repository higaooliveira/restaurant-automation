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
        val company = Company(UUID.randomUUID(), "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val id = UUID.randomUUID()
        val expectedBoard = Optional.of(Board(id, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(id)).thenReturn(expectedBoard)

        val actualBoard = service.getById(id)
        Assertions.assertEquals(expectedBoard.get(), actualBoard)
        Assertions.assertEquals(expectedBoard.get().id, actualBoard.id)
    }

    @Test
    fun getAllBoardTest() {
        val company = Company(UUID.randomUUID(), "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoardList = listOf(
            Board(UUID.randomUUID(), 1L, "www.foobar.com.br", company),
            Board(UUID.randomUUID(), 2L, "www.foobar.com.br", company)
        )
        BDDMockito.`when`(repository.findAll()).thenReturn(expectedBoardList)

        val actualBoardList = service.getAll()
        Assertions.assertEquals(expectedBoardList, actualBoardList)
    }

    @Test
    fun createBoardTest() {
        val id = UUID.randomUUID()
        val boardDto = CreateBoardDto(1L, id)
        val company = Company(id, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Board(id, 1L, "/tmp/board_1_1.png", company)
        val content = "${expectedBoard.company}_${expectedBoard.id}_${expectedBoard.number}"
        BDDMockito.`when`(companyService.getById(id)).thenReturn(company)
        BDDMockito.doNothing().`when`(qrCodeWriter).write(expectedBoard.qrCodeLink, content)

        BDDMockito.`when`(repository.save(ArgumentMatchers.any(Board::class.java))).thenReturn(expectedBoard)
        val returnedBoard = service.create(boardDto)

        Assertions.assertEquals(expectedBoard, returnedBoard)
    }

    @Test
    fun deleteBookTest() {
        val id = UUID.randomUUID()
        val company = Company(id, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Optional.of(Board(id, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(id)).thenReturn(expectedBoard)
        service.delete(expectedBoard.get().id!!)
        BDDMockito.verify(repository, Mockito.times(1)).deleteById(expectedBoard.get().id)
    }
}
