package com.example.comics.model

import com.example.comics.R
import com.example.comics.core.ResourceBuilder
import com.example.comics.data.DataModel
import com.example.comics.data.ItemModel
import com.example.comics.data.ItemVO
import com.example.comics.data.ResultModel
import com.example.comics.data.ThumbnailModel
import com.example.comics.model.ComicsMapper
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ComicsMapperTest {

    private val resourceBuilder: ResourceBuilder = mockk()

    private lateinit var mapper: ComicsMapper

    @Before
    fun setup(){
        mapper = ComicsMapper(resourceBuilder)
    }

    @Test
    fun `WHEN toItemVO is execute without description THEN return list ItemVO`() {
        val mockItemModel = ItemModel(data = DataModel(results = listOf(
            ResultModel("titleMock", null, ThumbnailModel("mock", "mock"))
        ))
        )

        coEvery { resourceBuilder.getString(R.string.without_description) } returns "mock"

        val expected = mockItemModel.data.results.map { ItemVO(
            image = "${it.thumbnail.path}.${it.thumbnail.extension}",
            title = it.title,
            subtitle = it.description ?: "mock"
        ) }

        val result = mapper.toItemVO(mockItemModel)

        assertEquals(result, expected)
    }

    @Test
    fun `WHEN toItemVO is execute with description THEN return list ItemVO`() {
        val mockItemModel = ItemModel(data = DataModel(results = listOf(
            ResultModel("titleMock", "mockDescription", ThumbnailModel("mock", "mock"))
        ))
        )

        coEvery { resourceBuilder.getString(R.string.without_description) } returns "mock"

        val expected = mockItemModel.data.results.map { ItemVO(
            image = "${it.thumbnail.path}.${it.thumbnail.extension}",
            title = it.title,
            subtitle = it.description ?: "mock"
        ) }

        val result = mapper.toItemVO(mockItemModel)

        assertEquals(result, expected)
    }
}