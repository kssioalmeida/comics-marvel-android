package com.example.comics.model

import com.example.comics.data.DataModel
import com.example.comics.data.ItemModel
import com.example.comics.data.ItemVO
import com.example.comics.data.ResultModel
import com.example.comics.data.ThumbnailModel
import com.example.comics.model.ComicsInteractor
import com.example.comics.model.ComicsInteractorImpl
import com.example.comics.model.ComicsMapper
import com.example.comics.model.ComicsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ComicsInteractorTest {

    private val repository: ComicsRepository = mockk()
    private val mapper: ComicsMapper = mockk()

    private lateinit var interactor: ComicsInteractor

    @Before
    fun setUp() {
        interactor = ComicsInteractorImpl(repository, mapper)
    }

    @Test
    fun `WHEN getComics execute THEN return success ItemVO`() = runBlocking {
        // Configuration
        val mockResultModel = ResultModel(
            "mockTitle",
            "mockDescription",
            ThumbnailModel(
            "mockPath",
                "mockExtension"
        )
        )

        val comicsData = ItemModel(DataModel(listOf(mockResultModel)))
        val itemVOs = comicsData.data.results.map {
            ItemVO(
                image = "${it.thumbnail.path}.${it.thumbnail.extension}",
                title = it.title,
                subtitle = it.description ?: "mock"
            )
        }

        coEvery { repository.getComics() } returns flow { emit(comicsData) }
        every { mapper.toItemVO(comicsData) } returns itemVOs

        // Execution
        val result = interactor.getComics().first()

        // Assertion
        assertEquals(itemVOs, result)
    }
}
