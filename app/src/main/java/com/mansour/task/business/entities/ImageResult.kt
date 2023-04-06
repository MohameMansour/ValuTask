package com.mansour.task.business.entities

import com.google.gson.annotations.SerializedName

data class ImageResult(
    val description: String,
    @SerializedName("price") val price: String,
    val id: Long,
    val image : String,
    val rating: Rating,
    val title: String
)

data class Rating(
    val count: Int,
    val rate: Double
)