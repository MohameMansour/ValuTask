package com.mansour.task.framework.presentation.features.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mansour.task.business.entities.ImageResult
import com.mansour.task.databinding.ItemImageBinding
import com.mansour.task.framework.presentation.callback.OnItemClickListener

class ImageHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageResult: ImageResult) {
        binding.imageResult = imageResult
    }

    companion object {
        fun from(parent: ViewGroup, onItemClickListener: OnItemClickListener<ImageResult>?): ImageHolder {
            val binding =
                ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            binding.listener = onItemClickListener
            return ImageHolder(binding)
        }
    }
}