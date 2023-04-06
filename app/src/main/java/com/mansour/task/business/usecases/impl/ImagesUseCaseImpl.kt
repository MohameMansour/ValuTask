package com.mansour.task.business.usecases.impl

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.business.repositories.abstraction.ImagesRepository
import com.mansour.task.business.usecases.abstraction.ImagesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesUseCaseImpl @Inject constructor(private val repository: ImagesRepository) :
    ImagesUseCase {
    override suspend fun getImages(): Flow<ImageModel> = repository.getImages()
}