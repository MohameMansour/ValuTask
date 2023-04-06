package com.mansour.task

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.business.repositories.abstraction.ImagesRepository
import com.mansour.task.business.repositories.impl.ImagesRepositoryImpl
import com.mansour.task.framework.datasources.remote.abstraction.ImagesDataSource
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryTest {

    private lateinit var repository: ImagesRepository

    private val dataSource: ImagesDataSource = mockkClass(ImagesDataSource::class)
    private val image = mockkClass(ImageModel::class)

    private val expectedGetImageSuccessResult = image
    private val expectedFailureResult = RuntimeException("Can't get the images")

    @Before
    fun setUp() {
        repository = ImagesRepositoryImpl(dataSource)
    }


    @Test
    fun `getImages with success response then return success`() = runBlocking {
        coEvery { dataSource.getImages() } returns image
        val response = repository.getImages()

        var success: ImageModel? = null
        var error: Throwable? = null
        response
            .catch { error = it }
            .collect { success = it }

        assertNotNull(success)
        assertNull(error)
        assertEquals(expectedGetImageSuccessResult, success)
    }

    @Test
    fun `getImages with failure response then return error`() = runBlocking {
        coEvery { dataSource.getImages() } answers { throw expectedFailureResult }
        val response = repository.getImages()

        var success: ImageModel? = null
        var error: Throwable? = null

        response
            .catch { error = it }
            .collect { success = it }

        assertNull(success)
        assertNotNull(error)
        assertEquals(expectedFailureResult.message, error!!.message)
    }
}