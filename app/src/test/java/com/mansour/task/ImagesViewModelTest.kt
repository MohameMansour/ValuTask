package com.mansour.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mansour.task.business.entities.ImageModel
import com.mansour.task.business.usecases.abstraction.ImagesUseCase
import com.mansour.task.framework.presentation.features.images.ImagesViewModel
import com.mansour.task.framework.utils.states.DataState
import io.mockk.coEvery
import io.mockk.mockkClass
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ImagesViewModel
    private val useCase: ImagesUseCase = mockkClass(ImagesUseCase::class)

    private val image = mockkClass(ImageModel::class)

    private val expectedGetImageSuccessResult = image
    private val expectedFailureResult = RuntimeException("Can't get the images")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getImages with success response then return success`() = runBlocking {
        coEvery { useCase.getImages() } answers { flow { emit(expectedGetImageSuccessResult) } }
        viewModel = ImagesViewModel(useCase)
        assertTrue(viewModel.imagesDataState.value is DataState.Success)
        val actual = (viewModel.imagesDataState.value as DataState.Success).data
        assertEquals(expectedGetImageSuccessResult, actual)
    }

    @Test
    fun `getImages with failure response then return error`() = runBlocking {
        coEvery { useCase.getImages() } answers { flow { throw  expectedFailureResult } }
        viewModel = ImagesViewModel(useCase)
        assertTrue(viewModel.imagesDataState.value is DataState.Failure)
        val actual = (viewModel.imagesDataState.value as DataState.Failure).throwable
        assertEquals(expectedFailureResult, actual)
    }
}