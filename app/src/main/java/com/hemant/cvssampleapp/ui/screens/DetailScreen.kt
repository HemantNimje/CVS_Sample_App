package com.hemant.cvssampleapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hemant.cvssampleapp.data.Item
import com.hemant.cvssampleapp.ui.components.PhotoDetails

@Composable
fun DetailScreen(
    item: Item,
    width: String,
    height: String,
    description: String,
    modifier: Modifier = Modifier
) {
    PhotoDetails(
        imageRes = item.media.media,
        title = item.title,
        description = description,
        width = width,
        height = height,
        author = item.author
    )
}