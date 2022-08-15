package com.hemant.cvssampleapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hemant.cvssampleapp.data.Item
import com.hemant.cvssampleapp.data.Media

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(
    list: List<Item>?,
    onGridItemClick: (Index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    list?.let {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(160.dp),
            content = {
                itemsIndexed(list) { index, item ->
                    GridItem(
                        item = item,
                        modifier = modifier
                            .clickable {
                                onGridItemClick(index)
                            }
                    )
                }
            },
            modifier = modifier.padding(16.dp)
        )
    }
}

@Composable
fun GridItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .padding(4.dp)
    ) {
        AsyncImage(
            model = item.media.media,
            contentDescription = item.description,
            modifier = modifier
                .height(180.dp)
                .width(180.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.labelMedium,
            modifier = modifier
                .padding(top = 4.dp, bottom = 16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true, device = Devices.PIXEL)
@Composable
fun GridItemPreview() {
    GridItem(
        Item(
            title = "07.14.2022",
            link = "https://www.flickr.com/photos/theweltyfamily/52284118094/",
            media = Media(
                media = "https://live.staticflickr.com/65535/52284118094_f43089e84d_m.jpg"
            ),
            date_taken = "2022-07-14T11:03:55-08:00",
            description = "Die Baumstachler teilen sich ein Gehege mit den Erdhörnchen, und sie vertragen sich ausgezeichnet miteinander.",
            published = "2022-08-14T01:15:02Z",
            author = "nobody@flickr.com (\"TheWeltyFamily\")",
            author_id = "15538284@N05",
            tags = "2022 theweltyfamily indianapolis indiana zoo indianapoliszoo animal july summer mammal porcupine"
        )
    )
}