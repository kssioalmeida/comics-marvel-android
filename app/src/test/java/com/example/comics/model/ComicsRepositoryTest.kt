package com.example.comics.model

import com.example.comics.data.DataModel
import com.example.comics.data.ItemModel
import com.example.comics.data.remote.ComicsService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class ComicsRepositoryTest {

    private val comicsService: ComicsService = mockk()
    private val comicsRepository: ComicsRepository = ComicsRepositoryImpl(comicsService)

    @Test
    fun `WHEN getComics execute THEN return success response ItemModel`() = runBlocking {
        // Configuration
        val itemModel = ItemModel(DataModel(listOf()))
        coEvery { comicsService.getComics(any(), any(), any()) } returns Response.success(itemModel)

        // Execution
        val flow: Flow<ItemModel> = comicsRepository.getComics()
        val result: ItemModel? = flow.singleOrNull()

        // Assertion
        assertEquals(itemModel, result)
    }
}
