package com.mansour.task.business.repositories.impl

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.business.repositories.abstraction.ImagesRepository
import com.mansour.task.framework.datasources.remote.abstraction.ImagesDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(private val dataSource: ImagesDataSource) :
    ImagesRepository {

    override suspend fun getImages(): Flow<ImageModel> = flow {
        emit(dataSource.getImages())
    }.flowOn(IO)

}