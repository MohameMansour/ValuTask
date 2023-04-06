package com.mansour.task

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.framework.datasources.remote.abstraction.ImagesDataSource
import com.mansour.task.framework.datasources.remote.impl.ImagesDataSourceImpl
import com.mansour.task.framework.datasources.remote.services.ImagesApi
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesDataSourceTest {
    private lateinit var dataSource: ImagesDataSource
    private val api = mockkClass(ImagesApi::class)
    private val image = mockkClass(ImageModel::class)

    private val expectedGetImageSuccessResult = image
    private val expectedFailureResult = RuntimeException("Can't get the images")

    @Before
    fun setUp() {
        dataSource = ImagesDataSourceImpl(api)
    }

    @Test
    fun `getImages with success response then return success`() = runBlocking {
        coEvery { api.getImages() } answers { expectedGetImageSuccessResult }
        val response = dataSource.getImages()

        assertNotNull(response)
        assertEquals(expectedGetImageSuccessResult, response)
    }

    @Test
    fun `getImages with failure response then return error`() = runBlocking {
        coEvery { api.getImages() } throws expectedFailureResult
        var response: RuntimeException? = null
        try {
            dataSource.getImages()
        } catch (e: RuntimeException) {
            response = e
        }

        assertNotNull(response)
        assertEquals(expectedFailureResult, response)
        assertEquals(expectedFailureResult.message, response?.message)
    }


}