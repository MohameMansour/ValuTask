package com.mansour.task.framework.presentation.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.mansour.task.business.entities.ImageResult

class ImageResultDiffCallback : DiffUtil.ItemCallback<ImageResult>() {
    override fun areItemsTheSame(oldItem: ImageResult, newItem: ImageResult) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageResult, newItem: ImageResult) =
        oldItem == newItem
}