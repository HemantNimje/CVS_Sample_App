package com.hemant.cvssampleapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hemant.cvssampleapp.ui.states.ProgressBarState

@Composable
fun ProgressBar(
    progressBarState: ProgressBarState,
    modifier: Modifier = Modifier
) {
    when (progressBarState) {
        is ProgressBarState.Hide -> {

        }
        is ProgressBarState.Show -> {
            LinearProgressIndicator(
                modifier = modifier
                    .fillMaxWidth(1f)
            )
        }
    }
}