package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.repository.BoardRepository
import com.higor.restaurantautomation.domain.service.contracts.BoardServiceContract
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import java.util.*

@SpringBootTest
@ContextConfiguration(classes=[BoardService::class])
class BoardServiceTest {

    @MockBean
    lateinit var repository: BoardRepository

    @MockBean
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var service: BoardService

    @Test
    fun getBoardTest() {
        val company =  Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Optional.of(Board(1L, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(this.repository.findById(1L)).thenReturn(expectedBoard)

        val actualBoard = this.service.getById(1L)
        Assertions.assertEquals(expectedBoard.get(), actualBoard)
        Assertions.assertEquals(expectedBoard.get().id, actualBoard.id)
    }

    @Test
    fun getAllBoardTest() {
        val company =  Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoardList = listOf(Board(1L, 1L, "www.foobar.com.br", company), Board(2L, 2L, "www.foobar.com.br", company))
        BDDMockito.`when`(this.repository.findAll()).thenReturn(expectedBoardList)

        val actualBoardList = this.service.getAll()
        Assertions.assertEquals(expectedBoardList, actualBoardList)
    }

    @Test
    fun deleteBookTest(){
        val company =  Company(1L, "John Doe", "johndoe@mock.com", "123456", "123456", "123456")
        val expectedBoard = Optional.of(Board(1L, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(this.repository.findById(1L)).thenReturn(expectedBoard)
        this.service.delete(expectedBoard.get().id!!)
        BDDMockito.verify(this.repository, Mockito.times(1)).deleteById(expectedBoard.get().id!!)
    }
}