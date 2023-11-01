package com.example.comics.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comics.MainDispatcherRule
import com.example.comics.data.ItemVO
import com.example.comics.model.ComicsInteractor
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ComicsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val interactor: ComicsInteractor = mockk()
    private lateinit var viewModel: ComicsViewModel

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        viewModel = ComicsViewModel(interactor)
    }

    @Test
    fun `WHEN getComics is execute on viewModel THEN return flow ItemVO AND set liveData`() = runBlocking {
        val expectedComicsList = listOf(ItemVO("mock", "mock", "mock"))
        coEvery { interactor.getComics() } returns flowOf(expectedComicsList)

        viewModel.getComics()

        assert(viewModel.comicsList.value == expectedComicsList)
    }
}