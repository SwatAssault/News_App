package com.rkhasanov.newsApp.presentation

sealed interface BasicRequestState {

    object Loading : BasicRequestState

    data class Error(
        val message: String
    ) : BasicRequestState

    object Success : BasicRequestState

}