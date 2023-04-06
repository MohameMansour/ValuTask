package com.mansour.task.framework.datasources.remote.services

import com.mansour.task.business.entities.ImageModel
import com.mansour.task.framework.utils.Constants.Network.ImagesEndPoints
import retrofit2.http.GET

interface ImagesApi {

    @GET(ImagesEndPoints.IMAGES)
    suspend fun getImages(): ImageModel

}