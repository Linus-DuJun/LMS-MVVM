package org.linus.base.platform.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {
    open val isLoading = MutableLiveData(false)
    private val _error = MutableLiveData<Throwable>()
    val error = _error as LiveData<Throwable>

    protected fun sendError(error: Throwable) {
        _error.postValue(error)
    }

    fun showLoading() = isLoading.postValue(true)
    fun hideLoading() = isLoading.postValue(false)

    protected fun CoroutineScope.safeLaunch(
        context: CoroutineContext = Dispatchers.IO,
        showLoading: Boolean = true, // whether need show loading view for this operation.
        block: suspend CoroutineScope.() -> Unit
    ) {
        if (showLoading) showLoading() // display loading view before HTTP request
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            sendError(throwable)
        }
        launch(context = context + exceptionHandler, block = block).invokeOnCompletion {
            if (showLoading) hideLoading() // hide loading view
        }
    }
}