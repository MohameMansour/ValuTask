package com.mansour.task.business.usecases.abstraction

import com.mansour.task.business.entities.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImagesUseCase {
    suspend fun getImages(): Flow<ImageModel>
}