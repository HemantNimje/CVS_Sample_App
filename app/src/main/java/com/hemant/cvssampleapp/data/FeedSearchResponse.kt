package com.hemant.cvssampleapp.data

import com.google.gson.annotations.SerializedName

data class FeedSearchResponse(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("generator") val generator: String,
    @SerializedName("items") val items: List<Item>
)

data class Item(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("media") val media: Media,
    @SerializedName("date_taken") val date_taken: String,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: String,
    @SerializedName("author") val author: String,
    @SerializedName("author_id") val author_id: String,
    @SerializedName("tags") val tags: String,
)

data class Media(
    @SerializedName("m") val media: String
)