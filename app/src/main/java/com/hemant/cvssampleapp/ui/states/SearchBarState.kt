package com.hemant.cvssampleapp.ui.states

sealed class SearchBarState {
    object Empty : SearchBarState()

    data class InputInProgress(
        val recentSearchResults: List<String>,
    ) : SearchBarState()

    object InputComplete : SearchBarState()
}
