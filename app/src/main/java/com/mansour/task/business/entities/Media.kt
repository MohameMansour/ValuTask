package com.mansour.task.business.entities

import com.google.gson.annotations.SerializedName

class Media(@SerializedName("media-metadata") val mediaMetadata: List<MediaMetadata>)