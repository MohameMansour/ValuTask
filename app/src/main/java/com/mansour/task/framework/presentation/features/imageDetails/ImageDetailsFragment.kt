package com.mansour.task.framework.presentation.features.imageDetails

import androidx.navigation.fragment.navArgs
import com.mansour.task.R
import com.mansour.task.databinding.FragmentImageDetailsBinding
import com.mansour.task.framework.presentation.features.base.BaseFragment


class ImageDetailsFragment : BaseFragment<FragmentImageDetailsBinding>() {
    private val args by navArgs<ImageDetailsFragmentArgs>()

    override fun bindViews() {
        binding.entity = args.entity
    }

    override fun getLayoutResId(): Int = R.layout.fragment_image_details

}