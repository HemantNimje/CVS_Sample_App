package com.hemant.cvssampleapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hemant.cvssampleapp.R
import com.hemant.cvssampleapp.ui.states.SearchBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchBarState: SearchBarState,
    onSearchTextFieldClick: () -> Unit,
    onSearchIconClick: (tag: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {

        var textFieldValue by remember {
            mutableStateOf("")
        }

        Column(
            modifier = modifier
        ) {
            SearchBarEditTextWithIcon(
                textFieldValue = textFieldValue,
                onValueChange = { textFieldValue = it },
                searchBarState = searchBarState,
                onSearchTextFieldClick = onSearchTextFieldClick,
                onSearchIconClick = { onSearchIconClick(textFieldValue) },
                modifier = modifier
            )

            when (searchBarState) {
                is SearchBarState.InputInProgress -> {
                    RecentSearchList(
                        recentSearchList = searchBarState.recentSearchResults,
                        onClick = {
                            textFieldValue = it
                            onSearchIconClick(textFieldValue)
                        }
                    )
                }
                else -> {

                }
            }
        }
    }
}

@Composable
fun SearchBarEditTextWithIcon(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    searchBarState: SearchBarState,
    onSearchTextFieldClick: () -> Unit,
    onSearchIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    if (interactionSource.collectIsPressedAsState().value) {
        onSearchTextFieldClick()
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { onValueChange(it) },
        label = {
            Text(text = "Search here")
        },
        trailingIcon = {
            SearchIcon(
                searchBarState = searchBarState,
                onSearchIconClick = onSearchIconClick
            )
        },
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth(1f)
    )
}

@Composable
private fun SearchIcon(
    searchBarState: SearchBarState,
    onSearchIconClick: () -> Unit,
) {
    IconButton(
        onClick = onSearchIconClick,
        enabled = searchBarState != SearchBarState.Empty
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = "Search Icon"
        )
    }
}

@Composable
fun RecentSearchList(
    recentSearchList: List<String>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        RecentSearchTitle(
            recentSearchItemCount = recentSearchList.size
        )
        LazyColumn {
            items(recentSearchList.reversed()) { recentSearchItem ->
                RecentSearchItem(
                    recentSearchItem = recentSearchItem,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun RecentSearchTitle(
    recentSearchItemCount: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (recentSearchItemCount > 0) {
            "recent search results"
        } else {
            "no recent search"
        },
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun RecentSearchItem(
    recentSearchItem: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = recentSearchItem,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(1f)
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            .clickable { onClick(recentSearchItem) }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun SearchBarEditTextWithIconPreview() {
    SearchBarEditTextWithIcon(
        textFieldValue = "",
        onValueChange = {},
        searchBarState = SearchBarState.Empty,
        onSearchTextFieldClick = {},
        onSearchIconClick = {}
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchListWithDataPreview() {
    RecentSearchList(
        recentSearchList = listOf(
            "First",
            "Second"
        ),
        onClick = {}
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchListWithoutDataPreview() {
    RecentSearchList(
        recentSearchList = listOf(),
        onClick = {}
    )
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchTitleWithoutDataPreview() {
    RecentSearchTitle(recentSearchItemCount = 0)
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchTitleWithSomeDataPreview() {
    RecentSearchTitle(recentSearchItemCount = 1)
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun RecentSearchItemPreview() {
    RecentSearchItem(
        recentSearchItem = "Porcupine",
        onClick = {}
    )
}