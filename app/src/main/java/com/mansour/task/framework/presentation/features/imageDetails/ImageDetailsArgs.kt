package com.mansour.task.framework.presentation.features.imageDetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ImageDetailsArgs(
    val image: String,
    val title: String,
    val description: String,
    val price: String,
    val rate: Float
) : Parcelable