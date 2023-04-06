package com.mansour.task.framework.presentation.callback

interface OnItemClickListener<T> {
    fun onItemClicked(item: T)
}