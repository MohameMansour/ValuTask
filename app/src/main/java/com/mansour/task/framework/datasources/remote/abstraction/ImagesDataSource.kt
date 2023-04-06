package com.mansour.task.framework.datasources.remote.abstraction

import com.mansour.task.business.entities.ImageModel

interface ImagesDataSource {
    suspend fun getImages(): ImageModel
}