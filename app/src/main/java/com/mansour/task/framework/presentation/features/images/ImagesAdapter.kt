package com.mansour.task.framework.presentation.features.images

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mansour.task.business.entities.ImageResult
import com.mansour.task.framework.presentation.callback.OnItemClickListener
import com.mansour.task.framework.presentation.diffCallback.ImageResultDiffCallback
import javax.inject.Inject

class ImagesAdapter @Inject constructor() :
    ListAdapter<ImageResult, ImageHolder>(ImageResultDiffCallback()) {
    private var onItemClickListener: OnItemClickListener<ImageResult>? = null
    fun listen(onItemClickListener: OnItemClickListener<ImageResult>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder.from(parent, onItemClickListener)
}