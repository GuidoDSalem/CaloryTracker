package com.practice.core.util

sealed class UIEvent {
    object Success: UIEvent()
    object NavigateUp: UIEvent()
    data class ShowSnackbar(val message: UiText): UIEvent()
}