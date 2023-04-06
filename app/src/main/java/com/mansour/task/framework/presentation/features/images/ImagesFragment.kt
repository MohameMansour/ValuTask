package com.mansour.task.framework.presentation.features.images

import androidx.fragment.app.viewModels
import com.mansour.task.R
import com.mansour.task.business.entities.ImageResult
import com.mansour.task.databinding.FragmentImagesBinding
import com.mansour.task.framework.presentation.callback.OnItemClickListener
import com.mansour.task.framework.presentation.features.base.BaseFragment
import com.mansour.task.framework.presentation.features.imageDetails.ImageDetailsArgs
import com.mansour.task.framework.utils.ext.navigateTo
import com.mansour.task.framework.utils.states.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImagesFragment : BaseFragment<FragmentImagesBinding>() {
    private val viewModel by viewModels<ImagesViewModel>()

    @Inject
    lateinit var adapter: ImagesAdapter

    override fun bindViews() {
        initUI()
        subscribeOnViewObservers()

    }

    private fun initUI() {
        adapter.listen(object : OnItemClickListener<ImageResult> {
            override fun onItemClicked(item: ImageResult) {
                val entity = ImageDetailsArgs(
                    item.image ?: "",
                    title = item.title,
                    description = item.description,
                    price = item.price,
                    rate = item.rating.rate.toString().toFloat()
                )
                navigateTo(ImagesFragmentDirections.openImageDetails(entity))
            }
        })
        binding.imagesRV.adapter = adapter
    }

    private fun subscribeOnViewObservers() {
        viewModel.imagesDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    binding.loading = false
                    adapter.submitList(it.data)
                }
                is DataState.Failure -> {
                    binding.loading = false
                    handleError(it.throwable)
                }
                DataState.Loading -> binding.loading = true
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_images
}