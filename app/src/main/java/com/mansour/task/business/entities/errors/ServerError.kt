package com.mansour.task.business.entities.errors

import com.google.gson.annotations.SerializedName

data class ServerError(@SerializedName("fault") val fault: Error)

data class Error(@SerializedName("faultstring") val message: String)

