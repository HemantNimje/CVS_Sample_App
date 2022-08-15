package com.hemant.cvssampleapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PhotoDetails(
    imageRes: String,
    title: String,
    description: String,
    width: String,
    height: String,
    author: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(1f)
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageRes),
            contentDescription = description,
            modifier = modifier
                .height(180.dp)
                .fillMaxWidth(1f)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Title: $title",
            style = MaterialTheme.typography.h6,
            modifier = modifier
                .padding(top = 16.dp)
        )
        Text(
            text = "Description: $description",
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier
                .padding(top = 8.dp)
        )
        Text(
            text = "Dimension: $width x $height",
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier
                .padding(top = 8.dp)
        )
        Text(
            text = "Author: $author",
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun PhotoDetailsPreview() {
    PhotoDetails(
        imageRes = "https://live.staticflickr.com/65535/52279941966_ffbea5c9e3_m.jpg",
        title = "Tierpark Berlin: Baumstachler",
        description = "Die Baumstachler teilen sich ein Gehege mit den Erdhörnchen, und sie vertragen sich ausgezeichnet miteinander.",
        width = "240",
        height = "180",
        author = "SebastianBerlin"
    )
}