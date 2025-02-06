package com.example.blogpost.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<T>(initialState: T) : ViewModel() {
    protected val mutableState = MutableStateFlow(initialState)
    val state: StateFlow<T> = mutableState
}