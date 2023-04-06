package com.mansour.task.framework.presentation.features.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mansour.task.business.entities.ImageModel
import com.mansour.task.business.usecases.abstraction.ImagesUseCase
import com.mansour.task.framework.utils.ext.catchError
import com.mansour.task.framework.utils.ext.launchIdling
import com.mansour.task.framework.utils.states.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val useCase: ImagesUseCase) : ViewModel() {
    private val _imagesDataState: MutableLiveData<DataState<ImageModel>> =
        MutableLiveData()
    val imagesDataState: LiveData<DataState<ImageModel>> get() = _imagesDataState

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launchIdling {
            useCase.getImages()
                .onStart { _imagesDataState.value = DataState.Loading }
                .catchError { _imagesDataState.value = DataState.Failure(it) }
                .collect { _imagesDataState.value = DataState.Success(it) }
        }
    }
}