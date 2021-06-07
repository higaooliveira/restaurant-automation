package com.higor.restaurantautomation.domain.service

import com.higor.restaurantautomation.adapters.repository.BoardRepository
import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import com.higor.restaurantautomation.utils.QrCodeWriter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import java.util.Optional
import java.util.UUID

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
    fun `Given a invalid Id, When try to find a board by Id, Then ResourceNotFound Exception should throw`() {
        val id = UUID.randomUUID()
        BDDMockito.`when`(repository.findById(id)).thenThrow(ResourceNotFound("Resource Not Found for passed id"))

        val exception = assertThrows<ResourceNotFound> { service.getById(id) }
        Assertions.assertEquals("Resource Not Found for passed id", exception.localizedMessage)
    }

    @Test
    fun `Given a valid board Id, When try to find a bord by Id, Then must a valid board return`() {
        val company = Company(
            id = UUID.randomUUID(),
            name = "John Doe",
            email = "johndoe@mock.com",
            password = "123456",
            phone = "123456",
            document = "123456"
        )
        val id = UUID.randomUUID()
        val expectedBoard = Optional.of(Board(id, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(id)).thenReturn(expectedBoard)

        val actualBoard = service.getById(id)
        Assertions.assertEquals(expectedBoard.get(), actualBoard)
        Assertions.assertEquals(expectedBoard.get().id, actualBoard.id)
    }

    @Test
    fun `Given a valid company, When Try to get all boards of this company, Then a list of Boards should return`() {
        val company = Company(
            id = UUID.randomUUID(),
            name = "John Doe",
            email = "johndoe@mock.com",
            password = "123456",
            phone = "123456",
            document = "123456"
        )
        val expectedBoardList = listOf(
            Board(UUID.randomUUID(), 1L, "www.foobar.com.br", company),
            Board(UUID.randomUUID(), 2L, "www.foobar.com.br", company)
        )
        BDDMockito.`when`(repository.findAllByCompanyId(company.id)).thenReturn(expectedBoardList)

        val actualBoardList = service.getAll(company.id)

        Assertions.assertEquals(expectedBoardList, actualBoardList)
    }

    @Test
    fun `Given a valid DTO, when try to create a board, Then repository should be called`() {
        val id = UUID.randomUUID()
        val boardDto = CreateBoardDto(1L, id)
        val company = Company(
            id = id,
            name = "John Doe",
            email = "johndoe@mock.com",
            password = "123456",
            phone = "123456",
            document = "123456"
        )
        val expectedBoard = Board(id, 1L, "/tmp/board_1_1.png", company)
        val content = "${expectedBoard.company}_${expectedBoard.id}_${expectedBoard.number}"
        BDDMockito.`when`(companyService.getById(id)).thenReturn(company)
        BDDMockito.doNothing().`when`(qrCodeWriter).write(expectedBoard.qrCodeLink, content)

        BDDMockito.`when`(repository.save(ArgumentMatchers.any(Board::class.java))).thenReturn(expectedBoard)
        val returnedBoard = service.create(boardDto)

        Assertions.assertEquals(expectedBoard, returnedBoard)
    }

    @Test
    fun `Given an existent board, When try to create the same board, Then ResourceAlreadyExists Exception should throw`() {
        val id = UUID.randomUUID()
        val boardDto = CreateBoardDto(1L, id)
        val company = Company(
            id = id,
            name = "John Doe",
            email = "johndoe@mock.com",
            password = "123456",
            phone = "123456",
            document = "123456"
        )
        val expectedBoard = Board(id, 1L, "/tmp/board_1_1.png", company)
        BDDMockito.`when`(companyService.getById(id)).thenReturn(company)
        BDDMockito.`when`(repository.save(ArgumentMatchers.any(Board::class.java))).thenReturn(expectedBoard)
        BDDMockito.`when`(service.create(boardDto)).thenThrow(ResourceAlreadyExists("Resource already exists"))

        val exception = assertThrows<ResourceAlreadyExists> {
            service.create(boardDto)
        }

        Assertions.assertNotNull(exception)
        Assertions.assertEquals("Resource already exists", exception.localizedMessage)
    }
    @Test
    fun `Given a valid board Id, When try to delete, Then repository should be called`() {
        val id = UUID.randomUUID()
        val company = Company(
            id = id,
            name = "John Doe",
            email = "johndoe@mock.com",
            password = "123456",
            phone = "123456",
            document = "123456"
        )
        val expectedBoard = Optional.of(Board(id, 1L, "www.foobar.com.br", company))
        BDDMockito.`when`(repository.findById(id)).thenReturn(expectedBoard)
        service.delete(expectedBoard.get().id)
        BDDMockito.verify(repository, Mockito.times(1)).deleteById(expectedBoard.get().id)
    }
}
