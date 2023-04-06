package com.mansour.task.framework.datasources.remote.impl

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.framework.datasources.remote.abstraction.ImagesDataSource
import com.mansour.task.framework.datasources.remote.services.ImagesApi
import javax.inject.Inject

class ImagesDataSourceImpl @Inject constructor(private val api: ImagesApi) : ImagesDataSource {
    override suspend fun getImages(): ImageModel = api.getImages()
}