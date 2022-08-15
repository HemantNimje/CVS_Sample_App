package com.hemant.cvssampleapp.ui.states

sealed class ProgressBarState {
    object Hide : ProgressBarState()
    object Show : ProgressBarState()
}
