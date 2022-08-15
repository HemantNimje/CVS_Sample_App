package com.hemant.cvssampleapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hemant.cvssampleapp.data.Item
import com.hemant.cvssampleapp.ui.components.Grid
import com.hemant.cvssampleapp.ui.components.ProgressBar
import com.hemant.cvssampleapp.ui.components.SearchBar
import com.hemant.cvssampleapp.ui.states.ProgressBarState
import com.hemant.cvssampleapp.ui.states.SearchBarState

@Composable
fun HomeScreen(
    searchBarState: SearchBarState,
    onSearchTextFieldClick: () -> Unit,
    onSearchIconClick: (tag: String) -> Unit,
    progressBarState: ProgressBarState,
    data: List<Item>?,
    onGridItemClick: (Index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SearchBar(
            searchBarState = searchBarState,
            onSearchTextFieldClick = onSearchTextFieldClick,
            onSearchIconClick = onSearchIconClick,
            modifier = modifier
                .padding(4.dp)
        )
        ProgressBar(
            progressBarState = progressBarState
        )
        Grid(
            list = data,
            onGridItemClick = onGridItemClick
        )
    }
}