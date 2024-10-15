package com.example.odigitaltest.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.Either
import com.example.odigitaltest.core.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    protected fun <T> Flow<Either<String, T>>.gatherRequest(
        state: MutableStateFlow<UIState<T>>,
        shouldResetStateOnCompletion: Boolean = false
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Error -> state.value = UIState.Error(it.value)
                    is Either.Success -> state.value =
                        UIState.Success(it.value)
                }
            }
        }.invokeOnCompletion {
            if (shouldResetStateOnCompletion)
                state.reset()
        }
    }

    protected fun <T> MutableStateFlow<UIState<T>>.reset() {
        value = UIState.Idle()
    }
}