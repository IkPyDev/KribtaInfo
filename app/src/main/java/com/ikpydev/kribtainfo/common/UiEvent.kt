package com.ikpydev.kribtainfo.common

sealed class UiEvent {
    data class Seccuss<T>(val data: T?) : UiEvent()
    data class Error(val message: String?) : UiEvent()
    object Loading : UiEvent()
    object EmptyList : UiEvent()
}