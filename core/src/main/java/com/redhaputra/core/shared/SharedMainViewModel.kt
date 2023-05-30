package com.redhaputra.core.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * View model responsible for preparing and managing the data for MainActivity.
 *
 * @see ViewModel
 */
class SharedMainViewModel : ViewModel() {

    private val _isShowBottomBar = MutableSharedFlow<Boolean>()
    val isShowBottomBar: SharedFlow<Boolean> = _isShowBottomBar.asSharedFlow()

    /**
     * Method to handle bottom bar show/hide
     */
    fun toggleBottomBar(visible: Boolean) {
        viewModelScope.launch {
            _isShowBottomBar.emit(visible)
        }
    }
}