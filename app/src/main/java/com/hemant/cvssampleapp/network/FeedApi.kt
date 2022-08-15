package com.hemant.cvssampleapp.network

import com.hemant.cvssampleapp.data.FeedSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FeedApi {

    companion object {
        const val BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne/"
    }

    @GET("?format=json&nojsoncallback=1")
    suspend fun getPhotos(
        @Query("tags") tag: String
    ): FeedSearchResponse
}

