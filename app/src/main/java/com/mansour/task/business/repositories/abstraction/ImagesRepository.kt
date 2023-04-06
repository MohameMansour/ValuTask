package com.mansour.task.business.repositories.abstraction

import com.mansour.task.business.entities.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    suspend fun getImages(): Flow<ImageModel>
}